package com.jason.sms.web;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jason.framework.web.support.ControllerSupport;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends ControllerSupport{
	
	public final static String PROJECT_BUILD_NUMBER = "1.0.0";
	
	@RequestMapping(value = { "/", "/welcome", "/home", "/index" }, method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		return "portal/index";
	}
	
}
