package com.yzz.uploadfile.service.Impl;

import com.yzz.uploadfile.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @ClassName FileServiceImpl
 * @Author yky
 * @Date 2021/1/10
 * @Version 1.0
 */
@Service
public class FileServiceImpl implements FileService {
	@Override
	public boolean upLoadFile(MultipartFile file) {
		String desPath = "/static";
		File file1 = new File(desPath);
		try {
			file.transferTo(file1);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private static void closeInputStream(InputStream is) {
		try {
			if (is != null) {
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文件下载
	 * @param path 服务器上的文件路径
	 * @param response
	 * @return
	 */
	public static int downloadFile(String path, HttpServletResponse response) {
		int result = 1;
		InputStream is = null;
		OutputStream os = null;
		try {
			File file = new File(path);
			// 清空response
			response.reset();
			// 设置response的Header
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(file.getName(), "utf-8"));
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			is = new FileInputStream(file);
			os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = is.read(b)) > 0) {
				os.write(b, 0, length);
			}
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			result = 0;
		} finally {
			closeInputStream(is);
			closeOutputStream(os);
		}

		return result;
	}

	private static void closeOutputStream(OutputStream os) {
		try {
			if (os != null) {
				os.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
