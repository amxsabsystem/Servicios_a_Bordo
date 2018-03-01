package com.aeromexico.pab.persistence.queryloader;

import com.aeromexico.pab.persistence.queryloader.dto.PreparedStatements;
import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Alfredo Estrada
 */
public class NativeQueryLaoder {
    private static PreparedStatements preparedStatements = null;
    public static PreparedStatements getPreparedStatements(){
        if(preparedStatements==null){        
            InputStream is=null;
            final String path = "/native-prepared-statements.xml";
            try {
                is = NativeQueryLaoder.class.getResourceAsStream(path);
                
                JAXBContext jaxbContext = JAXBContext.newInstance(PreparedStatements.class);

                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                preparedStatements = (PreparedStatements) jaxbUnmarshaller.unmarshal(is);
                

            } catch (JAXBException e) {
                e.printStackTrace(System.err);
            }
        }
        return preparedStatements;
    }  
    public static void main(String[] args) {
        System.out.println(getPreparedStatements());
    }
}
