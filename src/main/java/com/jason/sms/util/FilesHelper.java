package com.jason.sms.util;

import java.io.File;
import java.util.regex.Matcher;

import org.apache.commons.lang.StringUtils;

public class FilesHelper {
	
	private FilesHelper() {
		
	}
	
	/**
	 * 后缀名称前插入字符串 拼装字符
	 * eg:123.jpg -> 123_${insertString}.jpg
	 * @param fileName
	 * @param insertString
	 * @return
	 */
	public static String insertString(String fileName, String insertString) {
		String fileNameWithoutExtension = getFileNameWithoutExtension(fileName);
		String extension = getFileExtensionWithDot(fileName);
		return String.format("%s%s%s%s", fileNameWithoutExtension,"_",insertString, extension);
	}
	/**
	 * 后缀名称前插入字符串 拼装字符
	 * eg:123.jpg -> 123${insertString}.jpg
	 * @param fileName
	 * @param insertString
	 * @return
	 */
	public static String insertStringNotSplit(String fileName, String insertString) {
		String fileNameWithoutExtension = getFileNameWithoutExtension(fileName);
		String extension = getFileExtensionWithDot(fileName);
		return String.format("%s%s%s%s", fileNameWithoutExtension,"",insertString, extension);
	}

	/**
	 * 后缀名称前插入字符串，拼装字符
	 * eg: resources/upload/1.jpg -> resources/upload/1{insertString}.jpg
	 * @param path
	 * @param insertString
	 * @return
	 */
	public static String insertStringForPathNotSplit(String path, String insertString){
		if (insertString == null) {
			insertString = "";
		}
		String fileName = getFileNameFormUrl(path);
		String filePath = getFilePathFormUrl(path);
		String truefileName = insertStringNotSplit(fileName, insertString);
		return String.format("%s%s", filePath,truefileName);
	}
	
	/**
	 * eg: resources/upload/1.jpg -> resources/upload/1_{insertString}.jpg
	 * @param path
	 * @param insertString
	 * @return
	 */
	public static String insertStringForPath(String path, String insertString){
		String fileName = getFileNameFormUrl(path);
		String filePath = getFilePathFormUrl(path);
		String truefileName = insertString(fileName, insertString);
		return String.format("%s%s", filePath,truefileName);
	}

	/**
	 * abc_180_180.jpg
	 * @param fileName  abc
	 * @param insertString 180_180
	 * @param extension .jpg
	 * @return
	 */
	public static String insertFileNameAndString(String fileName, String insertString,String extension){
		return String.format("%s_%s%s", fileName, insertString, extension);
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileNameWithoutExtension(String fileName) {
		if (fileName.indexOf(".") != -1) {
			return fileName.substring(0, fileName.lastIndexOf("."));
		}
		return fileName;
	}

	/**
	 * .后缀名
	 * @param fileName
	 * @return
	 */
	public static String getFileExtensionWithDot(String fileName) {
		if (fileName.indexOf(".") != -1) {
			return fileName.substring(fileName.lastIndexOf("."));
		}
		return fileName;
	}

	/**
	 * eg:xxx/1.jpg -> 1.jpg
	 * @param path
	 * @return
	 */
	public static String getFileNameFormUrl(String path){
		String fileName = path.substring(path.lastIndexOf("/")+1);
		return fileName;
	}

	/**
	 * eg:xxx/1.jpg -> xxx/
	 * @param path
	 * @return
	 */
	public static String getFilePathFormUrl(String path){
		String filePath = path.substring(0,path.lastIndexOf("/")+1);
		return filePath;
	}
	
	/**
	 * 拼接路径
	 * @param base
	 * @param paths
	 * @return
	 */
	public static String join(String base, String... paths) {
		StringBuilder buf = new StringBuilder().append(base);
		for (String path : paths) {
			buf.append(File.separator).append(path);
		}
		return asPlatform(buf.toString());
	}
	
	/**
	 * 转换平台路径
	 * @param path
	 * @return
	 */
	public static String asPlatform(String path) {
		if (StringUtils.isBlank(path)) {
			return path;
		}
		return path.replaceAll("[/|\\\\]+", Matcher.quoteReplacement(File.separator));
	}
}
