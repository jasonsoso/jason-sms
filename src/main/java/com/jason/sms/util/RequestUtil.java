package com.jason.sms.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理Request的工具
 * @author Jonh
 *
 */
public class RequestUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestUtil.class);
	
	private RequestUtil() {
		
	}
	
	/**
	 * request的全部参数转换成map
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> getMap(HttpServletRequest request){
		return request.getParameterMap();
	}
	
	/**
	 * request的部分参数转换成map：
	 * 根据的keys指定的的Key从request取出值放到Map中
	 * @param request
	 * @param keys需要转的key,多个key用豆号隔开
	 * @return
	 */
	public static Map<String,Object> getMap(HttpServletRequest request,String keys){
		String[] split = keys.split(",");
		Map<String,Object> map = new HashMap<String, Object>(split.length);
		for (String key : split) {
			map.put(key, request.getParameter(key));
		}
		return map;
	}
	
	/**
	 * 取得语言
	 * @param request
	 * @return
	 */
	public static String getLang(HttpServletRequest request){
		String pLang = request.getParameter("lang");//优先
		String sLang = (String) request.getSession().getAttribute("lang");//其次
		String lang = pLang != null?pLang:sLang;
		
		if (lang == null){
			String hLang = request.getHeader ("accept-language") ;
			hLang = hLang==null?"en":hLang;
			
			hLang = hLang.split(",")[0];
			hLang = hLang.split("-")[0];
			
			if ("zh".equals(hLang)){
				lang ="cn";
			}else if ("ja".equals(hLang)){
				lang = "jp";
			}else if ("es".equals(hLang)){
				lang = "es";
			}else{
				lang = "en";
			}
		}
		
		return lang;
	}
	
	/**
	 * request的部分参数转换成map：
	 * @param request
	 * @param prefix字段的前缀
	 * @param keys
	 * @return
	 */
	public static Map<String,Object> getMap(HttpServletRequest request,String prefix,String keys){
		String[] split = keys.split(",");
		Map<String,Object> map = new HashMap<String, Object>(split.length);
		for (String key : split) {
			map.put(key, request.getParameter(prefix + '.'+ key));
		}
		return map;
	}

	
	/**
	 * 将map转换成bean对象：
	 * 根据Map的keyValues创建Bean对象
	 * @param theClass
	 * @param keyValues
	 * @return
	 */
	public static <T> T getBean(Class<T> theClass,Map<String,Object> keyValues){
		T bean = null;
		try {
			bean = theClass.newInstance();
			BeanUtils.populate(bean, keyValues);
		} catch (Exception e) {
			LOGGER.error("map转换成bean对象出错", e);
		}
		return bean;
	}

	/**
	 * 将map转换成bean对象：
	 * 根据Map的keyValues创建Bean对象
	 * @param theClass
	 * @param keyValues
	 * @return
	 */
	public static <T> T getBean(T bean,Map<String,Object> keyValues){
		try {
			BeanUtils.populate(bean, keyValues);
		} catch (Exception e) {
			LOGGER.error("map转换成bean对象出错", e);
		}
		return bean;
	}
	
}
