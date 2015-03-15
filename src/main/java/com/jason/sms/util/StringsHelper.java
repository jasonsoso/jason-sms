package com.jason.sms.util;

/**
 * 字符串 辅助类
 * 
 * @author Jason
 */
public final class StringsHelper {

	private StringsHelper() {

	}

	public static String suffix(String filename) {
		if (isBlank(filename)) {
			return "";
		}
		int dotIndex = filename.lastIndexOf('.');
		if (dotIndex == -1) {
			return "";
		}
		return filename.substring(dotIndex + 1);
	}

	public static boolean isBlank(String text) {
		if (isEmpty(text)) {
			return true;
		}
		int length = text.length();
		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(text.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * eg : null => true eg : "" => true
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isEmpty(String text) {
		if (null == text) {
			return true;
		}
		return text.length() <= 0;
	}

	/**
	 * 首字母大写
	 * 
	 * @author blue
	 * @date 2014年9月29日 上午11:22:50
	 * @return String
	 */
	public static String headUpperCase(String str) {
		if (str.length() >= 1) {
			str = str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
		}
		return str;
	}

	/**
	 * 
	 * @Description: 将Object转成String
	 * @author blue
	 * @date 2014年11月13日 下午4:56:12
	 * @param object
	 * @return
	 * @return String
	 */
	public static String parString(Object object) {
		if (object == null) {
			return null;
		} else {
			return object.toString();
		}

	}
}
