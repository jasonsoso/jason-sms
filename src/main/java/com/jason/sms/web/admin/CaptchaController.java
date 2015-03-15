package com.jason.sms.web.admin;

import java.awt.Color;
import java.awt.Font;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import nl.captcha.Captcha;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.renderer.DefaultWordRenderer;
import nl.captcha.text.renderer.WordRenderer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.ImmutableList;
import com.jason.framework.web.support.ControllerSupport;
import com.jason.sms.util.HttpCaptchaUtils;

/**
 * 验证码
 * @author Jason
 * @data 2015-3-15 下午01:11:30
 */
@Controller
public class CaptchaController extends ControllerSupport {

	private static final List<Font> FONTS = ImmutableList.of(
															new Font("Courier New", Font.ITALIC, 40), 
															new Font("Arial", Font.ITALIC, 40),
															new Font("Times New Roman", Font.ITALIC, 40),
															new Font("Verdana", Font.ITALIC, 40)
														);
	private static final List<Color> COLORS = ImmutableList.of(Color.black);
	private static final WordRenderer RENDERER = new DefaultWordRenderer(COLORS, FONTS);

	/**
	 * 
	 * @param session
	 * @param out
	 */
	@RequestMapping(value = "/captcha/", method = RequestMethod.GET)
	public void captcha(HttpSession session, OutputStream out) {
		Captcha captcha = new Captcha.Builder(200, 50).addText(RENDERER).addNoise().build();
		CaptchaServletUtil.writeImage(out, captcha.getImage());
		HttpCaptchaUtils.storeCaptcha(captcha, session);
	}
}
