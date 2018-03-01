package com.aeromexico.pab.backend.remote.ejb.mediastorage;

import com.aeromexico.pab.backend.Constantes;
import com.aeromexico.pab.backend.mediastorage.MediaInfoDTO;
import com.aeromexico.pab.backend.mediastorage.StorageServiceFacadeRemote;
import com.aeromexico.pab.entity.Multimedio;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.imageio.IIOException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alfredo Estrada
 */
@Stateless(
    name ="storageService_RSB",
    mappedName = "StorageService_RSB",
    description = "StorageServiceFacadeRemote-Stateles-Session EJB-3.1"
)
public class StorageServiceFacade implements StorageServiceFacadeRemote {
    private File fileTo_AMX_PAB_MEDIA_FS_STORAGE=null;    
        
    public static final String URL_BASE_PATTERN  = "${context}${mediaHashID}${queryStringPosfix}";    
    
    private static String      AMX_PAB_MEDIA_FS_WEBCONTEXT  = null;
    
    public static final String PREFIX_RESOURCE = "RESOURCE_";
    
    private final static Logger logger = Logger.getLogger(StorageServiceFacadeRemote.class.getName());
    
    @Resource 
    SessionContext ctx;
    
	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;
    
    private static HashMap<String,MediaInfoDTO> presavedStorage;
	
	protected EntityManager getEntityManager() {
		return em;
	}
    
    @PostConstruct
    @TransactionAttribute(NOT_SUPPORTED)
    public void init(){
        AMX_PAB_MEDIA_FS_WEBCONTEXT = System.getProperty("AMX_PAB_MEDIA_FS_WEBCONTEXT");
        if(AMX_PAB_MEDIA_FS_WEBCONTEXT == null){
            logger.log(Level.FINER,"init: AMX_PAB_MEDIA_FS_WEBCONTEXT Not in System.getProperty!");
           AMX_PAB_MEDIA_FS_WEBCONTEXT = "/media/"; 
        }
        logger.log(Level.FINER,"init: AMX_PAB_MEDIA_FS_WEBCONTEXT="+AMX_PAB_MEDIA_FS_WEBCONTEXT);
    }
    
    @PreDestroy
    @TransactionAttribute(NOT_SUPPORTED)
    public void preDestroy(){        
        final HashMap<String, MediaInfoDTO> pssM = getPresavedStorage();
        if(!pssM.isEmpty()){
            logger.log(Level.FINER,"predestroy: NOT SAVED, then destroy PresavedStorage URL's:"+pssM.keySet());
        }else{
            logger.log(Level.FINER,"predestroy: ...ok, PresavedStorage  is empty.");
        }
        getPresavedStorage().clear();
        logger.log(Level.FINER,"predestroy: clear PresavedStorage !!");
    }

    @Override
    @TransactionAttribute(SUPPORTS)
    public String saveOrUpdateMedia(byte[] content, String fileName, String mimeType,String httpSessionID) throws IOException {
        return saveOrUpdateMultimedio(content, fileName, mimeType,httpSessionID).getUrl();
    }
    
    @Override
    @TransactionAttribute(SUPPORTS)
    public Multimedio saveOrUpdateMultimedio(byte[] content,String fileName,String mimeType,String httpSessionID) throws IOException {
        MediaInfoDTO mi=new MediaInfoDTO(fileName, mimeType);
        
        mi.setResourceContent(content);
        mi.setCheckSumMD5(MediaInfoDTO.calculateMD5(content));
        mi.setHttpSessionID(httpSessionID);
        
        final String checkSumMD5 = mi.getCheckSumMD5();
        String relativeURLExpanded = URL_BASE_PATTERN.
                replace("${context}"            , AMX_PAB_MEDIA_FS_WEBCONTEXT).
                replace("${mediaHashID}"        , checkSumMD5).
                replace("${queryStringPosfix}"  , getQueryStringPosfix(mi.getMimeType()));
        
        mi.setUrl(relativeURLExpanded);
        
        getPresavedStorage().put(relativeURLExpanded, mi);

        final Map<String, Object> contextData = ctx.getContextData();
        logger.log(Level.FINER,"saveOrUpdateMedia: contextData.keys="+contextData.keySet());
        logger.log(Level.FINER,"saveOrUpdateMedia: Saved in memory: url="+relativeURLExpanded+", "+content.length+" bytes, PresavedStorage.keys="+getPresavedStorage().keySet());

        Multimedio m=new Multimedio();
        
        m.setHashMd5(checkSumMD5);
        m.setUrl(relativeURLExpanded);
        m.setUrlLocal(relativeURLExpanded);
        m.setMimeType(mimeType);
        m.setSize(content.length);
        m.setFileName(fileName);
        
        return m;
    }

    @Override
    @TransactionAttribute(SUPPORTS)
    public void commitSaveOrUpdateMedia(String urlUploaded) throws IOException {
        MediaInfoDTO mi=null;
        mi = getPresavedStorage().get(urlUploaded);
        if(mi == null){
            throw new IOException("Resorurce NOT FOUND");
        }
        File fo=null;
        OutputStream os =null;
        InputStream is=null;
        try{
            String finalName = "${prefixResource}${mediaHashID}${queryStringPosfix}";
            String finalNameExpanded = finalName.
                        replace("${prefixResource}", PREFIX_RESOURCE).
                        replace("${mediaHashID}", mi.getCheckSumMD5()).
                        replace("${queryStringPosfix}", getQueryStringPosfix(mi.getMimeType()));

            is = new ByteArrayInputStream(mi.getResourceContent());
            fo = new File(getFileTo_AMX_PAB_MEDIA_FS_STORAGE(),finalNameExpanded);
            os = new FileOutputStream(fo);

            byte[] buffer = new byte[1024*32];
            int r;

            while((r = is.read(buffer, 0, buffer.length))!= -1){
                os.write(buffer, 0, r);
                os.flush();
            }
            os.close();
            is.close();
            buffer = null;
            is=null;
            
            logger.log(Level.FINER,"commitSaveOrUpdateMedia: Saved in FS: url="+urlUploaded+", file="+fo+", r="+r);
            
            mi.setResourceContent(null);
            getPresavedStorage().remove(urlUploaded);            
        }catch(IOException ioe){
            //ioe.printStackTrace(System.err);
            logger.log(Level.SEVERE,ioe.getMessage(), ioe);
        }
    }
    
    @Override
    @TransactionAttribute(NOT_SUPPORTED)
    public List<String> getNotCommitedList(String httpSessionID){
        List<String> urlList=new ArrayList<>();
        for(MediaInfoDTO m:getPresavedStorage().values()){
            boolean added=false;
            if(m.getHttpSessionID().equalsIgnoreCase(httpSessionID)){
                urlList.add(m.getUrl());
                added=true;
            }
            logger.log(Level.FINER,"("+(added?"+":" ")+") ["+m.getUrl()+"] sessionId="+m.getHttpSessionID()+", ResourceContent: size="+m.getResourceContent().length+" bytes");
        }
        return urlList;
    }
    
    @Override
    @TransactionAttribute(NOT_SUPPORTED)
    public void forgetUncommited(String urlUploaded){
        getPresavedStorage().remove(urlUploaded);
    }
    
    /**
     * Have a Nice day All Gonet´s buddies :D !!
     * 
     * @param httpSessionID 
     */
    @Override
    @TransactionAttribute(NOT_SUPPORTED)
    public void forgetUncommitedInSession(String httpSessionID){
        List<String> urlList=new ArrayList<>();
        for(MediaInfoDTO m:getPresavedStorage().values()){
            boolean removed=false;
            if(     httpSessionID == null || 
                    (httpSessionID != null && m.getHttpSessionID().equalsIgnoreCase(httpSessionID))){
                removed=true;
                urlList.add(m.getUrl());
            }
            logger.log(Level.FINER,"("+(removed?"X":" ")+") ["+m.getUrl()+"] sessionId="+m.getHttpSessionID()+", ResourceContent: size="+m.getResourceContent().length+" bytes");
        }
        for(String u: urlList){
            forgetUncommited(u);
        }
    }

    @Override
    @TransactionAttribute(SUPPORTS)
    public void deleteMedia(String url) {
        
        logger.log(Level.FINER,"deleteMedia ("+url+"):");
        
        String embededURL = url.substring(url.indexOf(AMX_PAB_MEDIA_FS_WEBCONTEXT));
        String embededHashInURL = embededURL.substring(AMX_PAB_MEDIA_FS_WEBCONTEXT.length());
        logger.log(Level.FINER,"deleteMedia : embededURL="+embededURL+", embededHashInURL="+embededHashInURL);
        MediaInfoDTO mi=null;
        byte[] mediaContent=null;
        mi = getPresavedStorage().get(embededURL);
        if(mi != null){
            logger.log(Level.FINER,"deleteMedia :  delete from MEMORY");            
            getPresavedStorage().remove(embededURL);
            return;
        }
        logger.log(Level.FINER,"deleteMedia : try search in FS: with embededHashInURL="+embededHashInURL);
        
        File f = null;
        try {
            f = locateMediaInFS(embededURL);
            final boolean deleted = f.delete();
            logger.log(Level.FINER,"deleteMedia : result:"+deleted);
        } catch (IOException ioe) {
            logger.log(Level.SEVERE,ioe.getMessage(), ioe);
        }
    }

    @Override
    @TransactionAttribute(SUPPORTS)
    public byte[] openMedia(String url) throws IOException {
        logger.log(Level.FINER,"openMedia ("+url+"):");
        
        String embededURL = url.substring(url.indexOf(AMX_PAB_MEDIA_FS_WEBCONTEXT));
        String embededHashInURL = embededURL.substring(AMX_PAB_MEDIA_FS_WEBCONTEXT.length());
        logger.log(Level.FINER,"openMedia: embededURL="+embededURL+", embededHashInURL="+embededHashInURL);
        MediaInfoDTO mi=null;
        byte[] mediaContent=null;
        mi = getPresavedStorage().get(embededURL);
        if(mi != null){
            logger.log(Level.FINER,"openMedia: <<---- return from MEMORY:");
            return mi.getResourceContent();
        } else {
            logger.log(Level.FINER,"openMedia: Not in memroy MEMORY? :PresavedStorage="+getPresavedStorage().keySet());
        }
        logger.log(Level.FINER,"openMedia: try search in FS: with embededHashInURL="+embededHashInURL);
        
        String fileNameStored=PREFIX_RESOURCE+embededHashInURL;

        File fileTo_Resource = null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024*32];
        int len;

        InputStream isResource = null;
        byte[] bytesStored=null;

        if(getFileTo_AMX_PAB_MEDIA_FS_STORAGE() ==null){        
            throw new IIOException("AMX_PAB_MEDIA_FS_STORAGE directory not found:");
        }        
        fileTo_Resource = new File(getFileTo_AMX_PAB_MEDIA_FS_STORAGE(),fileNameStored);
        
        logger.log(Level.FINER,"openMedia: fileTo_Resource="+fileTo_Resource);
        
        if(!fileTo_Resource.exists()){
            throw new IIOException("File not exist:"+fileTo_Resource);
        }
        if(!fileTo_Resource.canRead()){
            throw new IIOException("File can not be read:"+fileTo_Resource);
        }
        isResource = new FileInputStream(fileTo_Resource);
        while ((len = isResource.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
            baos.flush();
        }
        isResource.close();
        baos.close();
        bytesStored = baos.toByteArray();
        logger.log(Level.FINER,"openMedia ("+url+"): return from FILESYSTEM :"+fileTo_Resource);
        return bytesStored;
    }

    @TransactionAttribute(SUPPORTS)
    private HashMap<String, MediaInfoDTO> getPresavedStorage() {
        logger.log(Level.FINER,"getPresavedStorage()");
        if(presavedStorage == null){
            presavedStorage = new HashMap<String, MediaInfoDTO>();
            logger.log(Level.FINER,"getPresavedStorage():\t creating HashMap!");
        }
        return presavedStorage;
    }
    
    @TransactionAttribute(SUPPORTS)
    private File getFileTo_AMX_PAB_MEDIA_FS_STORAGE() {
        if(fileTo_AMX_PAB_MEDIA_FS_STORAGE == null){
            String AMX_PAB_MEDIA_FS_STORAGE = System.getProperty(Constantes.AMX_PAB_MEDIA_FS_STORAGE);
            if(AMX_PAB_MEDIA_FS_STORAGE == null){
                AMX_PAB_MEDIA_FS_STORAGE = "./";
            }
            fileTo_AMX_PAB_MEDIA_FS_STORAGE = new File(AMX_PAB_MEDIA_FS_STORAGE);
            if(!fileTo_AMX_PAB_MEDIA_FS_STORAGE.canWrite()){
                throw new IllegalStateException("Can´t write into Directory:"+fileTo_AMX_PAB_MEDIA_FS_STORAGE);
            }
            logger.log(Level.FINER,"----------->>> LocalFileStorageService:AMX_PAB_MEDIA_FS_STORAGE="+AMX_PAB_MEDIA_FS_STORAGE+", fileTo_AMX_PAB_MEDIA_FS_STORAGE="+fileTo_AMX_PAB_MEDIA_FS_STORAGE);
        }
        return fileTo_AMX_PAB_MEDIA_FS_STORAGE;
    }
    
    @TransactionAttribute(SUPPORTS)
    private String getQueryStringPosfix(String mimeType) {
        String queryStringPosfix=null;
        if(mimeType.equalsIgnoreCase("image/jpeg")||mimeType.equalsIgnoreCase("image/jpg")){
            queryStringPosfix = ".jpg";
        } else if(mimeType.equalsIgnoreCase("image/png")){
            queryStringPosfix = ".png";
        } else if(mimeType.equalsIgnoreCase("image/gif")){
            queryStringPosfix = ".gif";
        } else if(mimeType.equalsIgnoreCase("image/giff")){
            queryStringPosfix = ".gif";
        } else if(mimeType.equalsIgnoreCase("application/pdf")){
            queryStringPosfix = ".pdf";
        } else {
            queryStringPosfix = ".resource";
        }
        return queryStringPosfix;
    }
    
    @TransactionAttribute(SUPPORTS)
    private File locateMediaInFS(String url) throws IOException{
        MediaInfoDTO mi=null;
        mi = getPresavedStorage().get(url);
        if(mi == null){
            throw new IOException("Resorurce NOT FOUND");
        }

        File fm=null;
        
        String finalName = "${prefixResource}${mediaHashID}${queryStringPosfix}";
        String finalNameExpanded = finalName.
                    replace("${prefixResource}", PREFIX_RESOURCE).
                    replace("${mediaHashID}", mi.getCheckSumMD5()).
                    replace("${queryStringPosfix}", getQueryStringPosfix(mi.getMimeType()));            

        fm = new File(getFileTo_AMX_PAB_MEDIA_FS_STORAGE(),finalNameExpanded);
                
        return fm;
    }
}
