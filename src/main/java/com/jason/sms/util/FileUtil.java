package com.jason.sms.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

	private FileUtil() {

	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 复制文件,必须保证目标文件夹是存在的
	 * 
	 * @param oldPath
	 * @param newPath
	 * @param delete
	 *            是否删除原文件
	 */
	public static void copyFile(String oldPath, String newPath, boolean delete) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1024];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				inStream.close();

				if (delete) {
					// 删除旧文件
					oldfile.delete();
				}
			}
		} catch (Exception e) {
			LOGGER.error("复制单个文件操作出错", e);
		}

	}

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * 
	 * @param dir
	 *            将要删除的文件目录
	 * @return boolean Returns "true" if all deletions were successful. If a
	 *         deletion fails, the method stops attempting to delete and returns
	 *         "false".
	 */
	public static void deleteDir(String filepath) throws IOException {
		File f = new File(filepath);// 定义文件路径
		if (f.exists() && f.isDirectory()) {// 判断是文件还是目录
			if (f.listFiles().length == 0) {// 若目录下没有文件则直接删除
				f.delete();
			} else {// 若有则把文件放进数组，并判断是否有下级目录
				File delFile[] = f.listFiles();
				int i = f.listFiles().length;
				for (int j = 0; j < i; j++) {
					if (delFile[j].isDirectory()) {
						deleteDir(delFile[j].getAbsolutePath());// 递归调用del方法并取得子目录路径
					}
					delFile[j].delete();// 删除文件
				}
			}
		}
	}

	/**
	 * 将String数据存为文件
	 */
	public static File getFileFromString(String content, String path) {
		BufferedOutputStream bos = null;
		File file = null;
		try {
			byte[] b = content.getBytes("UTF-8");
			file = new File(path);
			FileOutputStream fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(b);
		} catch (Exception e) {
			LOGGER.error("String转文件异常", e);
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					LOGGER.error("关闭流异常", e1);
				}
			}
		}
		return file;
	}

	/**
	 * 获得指定文件的byte数组
	 */
	public static byte[] getBytes(String filePath) {
		byte[] buffer = null;
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			LOGGER.error("文件找不到", e);
		} catch (IOException e) {
			LOGGER.error("IO操作异常", e);
		}
		return buffer;
	}
}
