package com.aeromexico.pab.web.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;

/**
 * com.aeromexico.sab.web.util.UpdateResourceBoundles
 * @author alfredo.estrada
 */
public class UpdateResourceBoundles {
    
	public static void main(String[] args) {		
		
		File   outputPathDir = null;
		InputStream  is = null; 
		OutputStream os = null; 
		
		if(args.length != 2 ){
			System.err.println("Usage: com.aeromexico.sab.web.util.UpdateResourceBoundles  [-stdin | pathToTranslatedResorces ] pathToOutputDirResourcesBoundles ");
			System.exit(1);			
		}
		
		try {
			if(args[0].equalsIgnoreCase("-stdin")){
				is = System.in;
			} else {
				is = new FileInputStream(args[0]);
			}
			
			
		}catch(FileNotFoundException fnfe){
			System.err.println("->File Not Found:"+fnfe.getMessage());
			System.exit(2);
		}
		try {
		
			outputPathDir =  new File(args[1]);
			if(		! outputPathDir.exists()      )
				throw new IllegalStateException("Dir:"+outputPathDir+" doesn't exists !");
			if(		! outputPathDir.isDirectory() )
				throw new IllegalStateException("Dir:"+outputPathDir+" doesn't exists !");
			if(		! outputPathDir.canWrite()    )
				throw new IllegalStateException("Dir:"+outputPathDir+" doesn't exists !");
			
		}catch(IllegalStateException iee){
			System.err.println("->Output dir error:"+iee.getMessage());
			System.exit(2);
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is,Charset.forName("UTF-8")));
		String line=null;
		int    numLines=0;
		LinkedHashMap<Integer,String>			mapForRSB				= new LinkedHashMap<>();
		LinkedHashMap<Integer,OutputStream>		mapForRSBOutoutStreams	= new LinkedHashMap<Integer,OutputStream>();
		try{
			
			
			for(line = null;(line=br.readLine())!= null; numLines++){
				//logger.debug("==>>["+numLines+"] ->"+line+"<-");
				if(line.trim().length()==0){
					numLines--;
					continue;
				}
				
				String values[] = line.split("\t");
				FileOutputStream fos = null;
				File fileRB = null;
				int numColumn=0;
				if(numLines == 0){
					numColumn=0;
					for(String colVal:values){
						String fileNameRB=colVal+".properties";
						try{
							fileRB = new File(outputPathDir,fileNameRB);
							fos = new FileOutputStream(fileRB);
							mapForRSB.             put(numColumn, colVal);
							mapForRSBOutoutStreams.put(numColumn, fos);
							System.err.println("\t=>["+numColumn+"]->["+fileNameRB+"]");
						}catch(FileNotFoundException fnfe){
							throw new IllegalStateException("Can't create ResourceBoundle File:"+fileNameRB);
						}finally{
							numColumn++;
						}
					}
				} else{
					numColumn=0;
					for(String colVal:values){
						String lineToWrite = escapeStringToJavaUnicodeString(colVal.trim()) + "\r\n";						
						//String lineToWrite = JavaUnicodeEscaper.unescapeJava(colVal.trim()) + "\r\n";
						try{							
							OutputStream fos2write = mapForRSBOutoutStreams.get(numColumn);
							System.err.print("\t["+mapForRSB.get(numColumn)+"]");
							fos2write.write(lineToWrite.getBytes());
							fos2write.flush();							
						}catch(IOException ioe){
							throw new IllegalStateException("Error writing ResourceBoundle File:"+mapForRSB.get(numColumn)+", lineToWrite:"+lineToWrite);
						}finally{
							numColumn++;
						}
					}
					System.err.println();
				}
				System.err.println("->Finish writing line["+numLines+"]!");								
			}
			for(OutputStream fos2close:mapForRSBOutoutStreams.values()){
				fos2close.close();
			}
			System.err.println("->closed, done.");
		}catch(IOException ioe){
			System.err.println("->Error reading line["+numLines+"]:"+ioe.getMessage());
			System.exit(2);
		}
	}
	
	public static final String escapeStringToJavaUnicodeString(String input){
	    StringBuilder b = new StringBuilder();

		for (char c : input.toCharArray()) {
			if (c >= 128)
				b.append("\\u").append(String.format("%04X", (int) c));
				//b.append("[\\u").append(String.format("%04X", (int) c)).append("]->[").append(c).append("]");
			else
				b.append(c);
		}

		return b.toString();
	}
}
