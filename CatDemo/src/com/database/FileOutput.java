package com.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.Part;

public class FileOutput {
	public static void saveFile(Part filePart, String savePath) throws IOException {
		InputStream fileContent = null;
		OutputStream outputStream = null;
		
		// 新檔名 = 目前日期時間 + 原檔名
		String fileName = generateFileName(filePart);
		
		// 將路徑和檔名與日期 組成
		// 如 (getServletContext())專案路徑/images/1/20236811011.jpg
		String imageUrl = savePath + File.separator + fileName;

		try {
			fileContent = filePart.getInputStream();
			// 新增資料夾
			File saveFile = new File(savePath);

			// 如果資料夾不存在
			if (!saveFile.exists()) {
				saveFile.mkdirs();
				System.out.println("建立成功");
			}

			outputStream = new FileOutputStream(imageUrl);

			byte[] buffer = new byte[1024];
			int bytesRead;

			while ((bytesRead = fileContent.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			
			// 得到資料夾的絕對路徑
			String absolutePath = saveFile.getAbsolutePath();
			System.out.println("檔案絕對路徑：" + absolutePath);

		} finally {
			if (fileContent != null) {
				fileContent.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	public static String generateFileName(Part filePart) {
		// 原檔名
		String fileName = filePart.getSubmittedFileName();
		// 目前日期
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddhhmm");
		String formatDate = format.format(date);

		return formatDate + fileName;
	}
}
