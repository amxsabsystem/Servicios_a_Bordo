package com.aeromexico.pab.backend.mediastorage;

import com.aeromexico.pab.entity.Multimedio;
import java.io.IOException;
import java.util.List;
import javax.ejb.Remote;

/**
 * @author Alfredo Estrada
 */
@Remote
public interface StorageServiceFacadeRemote {

    @Deprecated
    String saveOrUpdateMedia(byte[] content,String fileName,String mimeType,String httpSessionID) throws IOException;
       
    void commitSaveOrUpdateMedia(String urlUploaded) throws IOException;
    
    List<String> getNotCommitedList(String httpSessionID);
    
    void forgetUncommited(String urlUploaded);
    
    void forgetUncommitedInSession(String httpSessionID);
    
    void deleteMedia(String url);
    
    byte[] openMedia(String url) throws IOException;    

    Multimedio saveOrUpdateMultimedio(byte[] content,String fileName,String mimeType,String httpSessionID) throws IOException;
    
}
