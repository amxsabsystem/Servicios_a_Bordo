package com.aeromexico.pab.backend.mediastorage;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alfredo Estrada
 */
public class MediaInfoDTO implements Serializable{
    private String fileName;    
    private String mimeType;
    private String url;
    private String checkSumMD5;
    private byte[] resourceContent;
    private String httpSessionID;
    
    public MediaInfoDTO() {
    }

    public MediaInfoDTO(String fileName, String mimeType) {
        this.fileName = fileName;
        this.mimeType = mimeType;
    }
    
    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the mimeType
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * @param mimeType the mimeType to set
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the checkSumMD5
     */
    public String getCheckSumMD5() {
        return checkSumMD5;
    }

    /**
     * @param checkSumMD5 the checkSumMD5 to set
     */
    public void setCheckSumMD5(String checkSumMD5) {
        this.checkSumMD5 = checkSumMD5;
    }

    /**
     * @return the resourceContent
     */
    public byte[] getResourceContent() {
        return resourceContent;
    }

    /**
     * @param resourceContent the resourceContent to set
     */
    public void setResourceContent(byte[] resourceContent) {
        this.resourceContent = resourceContent;
    }
    
    public static synchronized String calculateMD5(byte[] content){
        String hashMD5=null;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(content);
            //hashMD5 = Base64.getEncoder().encodeToString(thedigest);
            hashMD5 = toHexString(thedigest);
            return hashMD5;
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace(System.err);
            return null;
        }
    }

    public static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString().toUpperCase();
    }

    /**
     * @return the httpSessionID
     */
    public String getHttpSessionID() {
        return httpSessionID;
    }

    /**
     * @param httpSessionID the httpSessionID to set
     */
    public void setHttpSessionID(String httpSessionID) {
        this.httpSessionID = httpSessionID;
    }
}
