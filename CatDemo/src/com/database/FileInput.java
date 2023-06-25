package com.database;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;

import java.util.Base64;



public class FileInput {
	public static String readFile(String filePath) throws IOException {
		// 讀取圖片檔案
	    InputStream inputStream = null;
	    ByteArrayOutputStream outputStream = null;
	    
	    // 產生 data URL
        String dataURL = null ;
	    try {
	        File file = new File(filePath);
	        inputStream = new FileInputStream(file);
	        outputStream = new ByteArrayOutputStream();

	        // 轉換為字节数組
	        byte[] buffer = new byte[8192];
	        int bytesRead;
	        while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
	            // 寫入到 ByteArrayOutputStream 中
	            outputStream.write(buffer, 0, bytesRead);
	        }

	        // 編碼為 Base64 字串
	        String base64Image = Base64.getEncoder().encodeToString(outputStream.toByteArray());

	        dataURL = "data:image/jpeg;base64," + base64Image;
	        
	    }catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (inputStream != null) {
	            try {
	                inputStream.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        if (outputStream != null) {
	            try {
	                outputStream.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
		return dataURL;
	}
}
