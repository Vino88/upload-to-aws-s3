package com.nostra.test.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class FileToBase64Util {
	public static void main(String[] args) throws Exception {
		
		  String resutl = encodeFileToBase64Binary("C://Users/lime/Pictures/80c2c682-e430-4ad4-b870-0620ed316618.png");
		  System.out.println(resutl);
		 
	}
	private static String encodeFileToBase64Binary(String fileName)
			throws IOException {

		File file = new File(fileName);
		byte[] bytes = loadFile(file);
		String encodedString  = Base64.getEncoder().encodeToString(bytes);

		return encodedString;
	}

	private static byte[] loadFile(File file) throws IOException {
	    InputStream is = new FileInputStream(file);

	    long length = file.length();
	    if (length > Integer.MAX_VALUE) {
	        // File is too large
	    }
	    byte[] bytes = new byte[(int)length];
	    
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }

	    if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }

	    is.close();
	    return bytes;
	}
}
