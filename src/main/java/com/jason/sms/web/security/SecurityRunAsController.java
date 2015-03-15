package com.jason.sms.web.security;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jason.framework.web.support.ControllerSupport;
import com.jason.sms.domain.security.User;
import com.jason.sms.service.security.UserRunAsService;
import com.jason.sms.service.security.UserService;
import com.jason.sms.util.shiro.MybatisShiroRealm.ShiroUser;
import com.jason.sms.util.shiro.ShiroUserUtils;

@Controller
@RequestMapping("/security/runas")
public class SecurityRunAsController extends ControllerSupport{

	private static final String REDIRECT_LIST = "redirect:/security/runas/list/";
	private static final String LIST = "security/runas/list";
	
    @Autowired
    private UserRunAsService userRunAsService;

    @Autowired
    private UserService userService;

    private User getCurrentUser(){
    	ShiroUser loginUser  = ShiroUserUtils.getCurrentUser();
    	return userService.lazyGet(loginUser.getId());
    }

    @RequestMapping(value = "/list/", method = GET)
    public String runasList(Model model) {
    	ShiroUser loginUser  = ShiroUserUtils.getCurrentUser();
    	
        model.addAttribute("fromUserIds", userRunAsService.findFromUserIds(loginUser.getId()));
        Map<Long,Object> toUserIdsMap= new HashMap<Long,Object>();
        List<Long> list = userRunAsService.findToUserIds(loginUser.getId());
        for (Long id:list) {
        	toUserIdsMap.put(id, id);
		}
        model.addAttribute("toUserIds", toUserIdsMap);

        List<User> allUsers = userService.queryAll();
        
        allUsers.remove(getCurrentUser());
        model.addAttribute("allUsers", allUsers);

        Subject subject = SecurityUtils.getSubject();
        model.addAttribute("isRunas", subject.isRunAs());
        if(subject.isRunAs()) {
        	ShiroUser previousUsername = (ShiroUser)subject.getPreviousPrincipals().getPrimaryPrincipal();
            model.addAttribute("previousUsername", previousUsername.getUsername());
        }

        return LIST;
    }



    @RequestMapping("/grant/{toUserId}")
    public String grant(
            @PathVariable("toUserId") Long toUserId,
            RedirectAttributes redirectAttributes) {
    	long userId = ShiroUserUtils.getCurrentUserId();
        if(toUserId.equals(userId)) {
            redirectAttributes.addFlashAttribute("msg", "自己不能切换到自己的身份");
            return REDIRECT_LIST;
        }

        userRunAsService.grantRunAs(userId, toUserId);
        redirectAttributes.addFlashAttribute("msg", "操作成功");
        return REDIRECT_LIST;
    }


    @RequestMapping("/revoke/{toUserId}")
    public String revoke(
            @PathVariable("toUserId") Long toUserId,
            RedirectAttributes redirectAttributes) {
    	long userId = ShiroUserUtils.getCurrentUserId();
        userRunAsService.revokeRunAs(userId, toUserId);
        redirectAttributes.addFlashAttribute("msg", "操作成功");
        return REDIRECT_LIST;
    }

    @RequestMapping("/switchTo/{switchToUserId}")
    public String switchTo(
            @PathVariable("switchToUserId") Long switchToUserId,
            RedirectAttributes redirectAttributes) {

    	User loginUser = getCurrentUser();
        Subject subject = SecurityUtils.getSubject();

        User switchToUser = userService.lazyGet(switchToUserId);
        if(loginUser.equals(switchToUser)) {
            redirectAttributes.addFlashAttribute("msg", "自己不能切换到自己的身份");
            return REDIRECT_LIST;
        }

        if(switchToUser == null || !userRunAsService.exists(switchToUserId, loginUser.getId())) {
            redirectAttributes.addFlashAttribute("msg", "对方没有授予您身份，不能切换");
            return REDIRECT_LIST;
        }
        ShiroUser shiroUser = new ShiroUser(switchToUser.getId(),switchToUser.getUsername(), switchToUser.getRoleId(),switchToUser.getRoleNamesAsString());
        
        subject.runAs(new SimplePrincipalCollection(shiroUser, ""));
        redirectAttributes.addFlashAttribute("msg", "操作成功");
        redirectAttributes.addFlashAttribute("needRefresh", "true");
        return REDIRECT_LIST;
    }

    @RequestMapping("/switchBack")
    public String switchBack(RedirectAttributes redirectAttributes) {

        Subject subject = SecurityUtils.getSubject();

        if(subject.isRunAs()) {
           subject.releaseRunAs();
        }
        redirectAttributes.addFlashAttribute("msg", "操作成功");
        redirectAttributes.addFlashAttribute("needRefresh", "true");
        return REDIRECT_LIST;
    }

}
