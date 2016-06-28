/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.xbwl.demo.web.account;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xbwl.demo.entity.User;
import com.xbwl.demo.service.account.AccountService;
import com.xbwl.demo.service.account.ShiroDbRealm.ShiroUser;
import com.xbwl.demo.utils.OnlineUsers;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * 
 * 真正登录的POST请求由Filter完成,
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private AccountService accountService;
    
	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		return "account/login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		return "account/login";
	}

	@RequestMapping(value="logout")
    public String logout() {
        Subject currentUser = SecurityUtils.getSubject();
        String name = "";
        if (currentUser.getSession() != null)
        {
            ShiroUser shiroUser = (ShiroUser)currentUser.getPrincipal();
            if(shiroUser != null){
                OnlineUsers.getInstance().removeUserById(shiroUser.loginName);
                name = shiroUser.loginName;
            }
            currentUser.logout();
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, name);
        }
        return "redirect:/login";
    }
	
	public void setOnlineUser(String userName, HttpServletRequest request){
        User user = accountService.findUserByLoginName(userName);
        OnlineUsers.getInstance().addUser(user);
    }
	
}
