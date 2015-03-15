package com.jason.sms.web.admin;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jason.framework.util.EncryptUtils;
import com.jason.framework.web.support.ControllerSupport;
import com.jason.sms.domain.security.User;
import com.jason.sms.service.security.UserService;
import com.jason.sms.util.HttpCaptchaUtils;
import com.jason.sms.util.exception.InvalidCaptchaException;
import com.jason.sms.util.shiro.ShiroUserUtils;
import com.jason.sms.web.HomeController;

/**
 * 管理员的登录，登出，修改密码
 * @author Jason
 *
 */
@Controller
public class AdminController extends ControllerSupport {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public static final int UNKNOWN_ACCOUT_ERROR_CODE = 1;
	public static final int LOCKED_ACCOUT_ERROR_CODE = 2;
	public static final int AUTHENTICATION_ERROR_CODE = 4;
	public static final int INVALID_CAPTCHA_ERROR_CODE = 8;
	public static final int OTHER_ERROR_CODE = 16;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/admin/login", method = GET)
	public String login() {
		return "admin/login";
	}

	@RequestMapping(value = "/admin/login", method = POST)
	public String login(HttpServletRequest request, HttpSession session) {
		logger.debug(String.format(
									"Handle login request with session[id=%s,createOn=%s,lastAccessedOn=%s]", 
									session.getId(),
									session.getCreationTime(),
									session.getLastAccessedTime()
								)
		);
		Subject subject = SecurityUtils.getSubject();

		if (subject.isAuthenticated()) {
			subject.logout();
		}
		try {

			checkCaptcha(request, session);
			AuthenticationToken token = createToken(request);

			subject.login(token);
		} catch (Exception e) {
			logger.error("login occur exception.", e);
			return "redirect:/admin/login?code=" + translateException(e);
		}
		return "redirect:/admin/dashboard/";
		
	}

	private void checkCaptcha(HttpServletRequest request, HttpSession session) {
		String captchaCode = WebUtils.getCleanParam(request, "captcha");
		HttpCaptchaUtils.checkCaptcha(captchaCode, session);
	}

	private AuthenticationToken createToken(HttpServletRequest request) {
		String username = WebUtils.getCleanParam(request, "username");
		String password = WebUtils.getCleanParam(request, "password");
		
		String passwordAsMd5 =EncryptUtils.md5(password + username);
		
		String rememberMeAsString = WebUtils.getCleanParam(request, "rememberMe");
		boolean rememberMe = false;
		if (null != rememberMeAsString) {
			rememberMe = Boolean.valueOf(rememberMeAsString);
		}
		String host = request.getRemoteHost();
		
		return new UsernamePasswordToken(username, passwordAsMd5, rememberMe, host);
	}

	private int translateException(Exception e) {

		if (e instanceof InvalidCaptchaException) {
			return INVALID_CAPTCHA_ERROR_CODE;
		}

		if (e instanceof UnknownAccountException) {
			return UNKNOWN_ACCOUT_ERROR_CODE;
		}

		if (e instanceof LockedAccountException) {
			return LOCKED_ACCOUT_ERROR_CODE;
		}

		if (e instanceof AuthenticationException) {
			return AUTHENTICATION_ERROR_CODE;
		}

		return OTHER_ERROR_CODE;
	}
	/**
	 * 登出
	 * @return
	 */
	@RequestMapping(value = "/admin/logout/", method = { GET, POST })
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/";
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	@RequiresPermissions("admin:changepwd")
	@RequestMapping(value = "/admin/changepwd/", method = GET)
	public String changePassword() {
		return "admin/changepwd";
	}
	/**
	 * 修改密码
	 * @param oldPassword
	 * @param password
	 * @param confirmPassword
	 * @param request
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("admin:changepwd")
	@RequestMapping(value = "/admin/changepwd/", method = POST)
	public String changePassword(
			@RequestParam("oldPassword") String oldPassword,
			@RequestParam("password") String password,
			@RequestParam("confirmPassword") String confirmPassword,
			HttpServletRequest request,RedirectAttributes redirectAttributes) {
		long userId = ShiroUserUtils.getCurrentUserId();
		User user =userService.lazyGet(userId);
		String userPassword = user.getPassword();
		
		String md5OldPassword =EncryptUtils.md5(oldPassword + user.getUsername());
		
		if(!StringUtils.equals(md5OldPassword, userPassword)){
			error(redirectAttributes,"你的输入原密码不正确");
		}else if(!StringUtils.equals(password, confirmPassword)){
			error(redirectAttributes,"你的输入新密码和确认密码不相同");
		}else{
			String md5Password =EncryptUtils.md5(password + user.getUsername());
			user.setPassword(md5Password);
			userService.update(user);
			success(redirectAttributes, "修改密码成功！");
		}
		return "redirect:/admin/changepwd/";
	}
	
	
	
	/**
	 * when login success,it always redirect to this method,just support GET method
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/dashboard/", method = GET)
	public String index(Model model) {
		/*Subject subject = SecurityUtils.getSubject();
		boolean fla = subject.isPermitted("authority:list");
		System.out.println("是否有authority:list权限："+fla);*/
		Properties props = System.getProperties();
		String v = HomeController.PROJECT_BUILD_NUMBER;
		model.addAttribute("props", props).addAttribute("version", v);
		return "admin/index";
	}

	/**
	 * must supported any method
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/deny/")
	public String deny() {
		return "admin/deny";
	}

	

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/head/", method = GET)
	public String head() {
		return "admin/head";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/foot/", method = GET)
	public String foot() {
		return "admin/foot";
	}

}
