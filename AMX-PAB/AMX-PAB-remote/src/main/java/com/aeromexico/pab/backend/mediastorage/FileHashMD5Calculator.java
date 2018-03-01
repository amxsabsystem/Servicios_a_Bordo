package com.aeromexico.pab.backend.mediastorage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * com.aeromexico.pab.backend.mediastorage.FileHashMD5Calculator
 * @author Alfredo Estrada
 */
public class FileHashMD5Calculator {
    public static void main(String[] args) {
        final String fileName = args[0];
        File  filePath = new File(fileName);
        FileInputStream fis = null;
        String hashMD5 = null;
        try{
            fis = new FileInputStream(filePath);
            OutputStream os = new ByteArrayOutputStream();                
            byte[] buffer = new byte[1024*32];
            int r;
            while((r = fis.read(buffer, 0, buffer.length))!= -1){
                os.write(buffer, 0, r);
                os.flush();
            }
            byte[] contentUploaded = ((ByteArrayOutputStream)os).toByteArray();
            hashMD5 = MediaInfoDTO.calculateMD5(contentUploaded);
            System.out.println(hashMD5);
        }catch(IOException ioe){
            ioe.printStackTrace(System.err);
            System.exit(1);
        }
    }
}
