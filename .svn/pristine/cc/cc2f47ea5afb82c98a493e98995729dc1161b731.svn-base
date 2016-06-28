package com.xbwl.demo.utils;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xbwl.demo.service.account.ShiroDbRealm.ShiroUser;

@Component
@Transactional
public class ShiroUserUtils {

	//取得当前用户
	public static ShiroUser getShiroUser(){
		return (ShiroUser)SecurityUtils.getSubject().getPrincipal();
	}
	
	//取得当前用户的id
	public static Long getUserId(){
		ShiroUser shiroUser = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
		return shiroUser.id;
	}
		
	//取得当前用户名
	public static String getName(){
		ShiroUser shiroUser = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
		return shiroUser.name;
	}
	
	//取得当前用户的登录名
	public static String getLoginName(){
		ShiroUser shiroUser = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
		return shiroUser.loginName;
	}
		
	//取得当前用户的角色ID
    public static Long getUserRoleId(){
        ShiroUser shiroUser = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
        return shiroUser.roleId;
    }
    
   //取得当前用户的角色名称
    public static String getUserRoleCode(){
        ShiroUser shiroUser = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
        return shiroUser.roleCode;
    }
    
}
