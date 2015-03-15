package com.jason.sms.util;

import javax.servlet.http.HttpSession;
import nl.captcha.Captcha;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.jason.sms.util.exception.InvalidCaptchaException;

public final class HttpCaptchaUtils {

	private static final String CAPTCHA_ATTR = "com.jason.sms.captcha";

	/**
	 * 
	 * @param captchaString
	 * @param session
	 */
	public static void checkCaptcha(String captchaString, HttpSession session) {

		// captcha.isCorrect maybe cause NullPointException,so must check the captchaString first.
		if (StringUtils.isBlank(captchaString)) {
			throw new InvalidCaptchaException("Blank captcha string!");
		}
		Assert.notNull(session, "None session!");
		
		Captcha captcha = (Captcha) session.getAttribute(CAPTCHA_ATTR);
		if (null == captcha) {
			throw new InvalidCaptchaException("Blank captcha in session!");
		}

		if (!captcha.isCorrect(captchaString)) {
			throw new InvalidCaptchaException("Bad captcha string");
		}

		synchronized (session) {
			session.removeAttribute(CAPTCHA_ATTR);
		}
	}

	/**
	 * 
	 * @param captcha
	 * @param session
	 */
	public static void storeCaptcha(Captcha captcha, HttpSession session) {
		synchronized (session) {
			session.setAttribute(CAPTCHA_ATTR, captcha);
		}
	}

	private HttpCaptchaUtils() {}
}
