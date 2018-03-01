package com.aeromexico.pab.web.jsf;

import java.io.InputStream;

/**
 *
 * @author Erick Diaz
 */
public class FileDTO {
    
    public FileDTO(){}
    
    private InputStream inputStreamFile;
    private String fileName;
    private String contentTypeUploaded;

    public InputStream getInputStreamFile() {
        return inputStreamFile;
    }

    public void setInputStreamFile(InputStream inputStreamFile) {
        this.inputStreamFile = inputStreamFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentTypeUploaded() {
        return contentTypeUploaded;
    }

    public void setContentTypeUploaded(String contentTypeUploaded) {
        this.contentTypeUploaded = contentTypeUploaded;
    }
    
    
            
}
