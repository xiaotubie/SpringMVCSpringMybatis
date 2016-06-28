package com.xbwl.demo.utils;

import java.util.ArrayList;

import com.xbwl.demo.entity.User;

/**
 * 用来管理在线用户
 */

public class OnlineUsers {

	private static OnlineUsers onlineUsers;
	
	private ArrayList<User> users;
	
	private OnlineUsers(){
		users = new ArrayList<User>();
	}
	
	public static OnlineUsers getInstance() {
		if (onlineUsers == null) {
			onlineUsers = new OnlineUsers();
		}
		return onlineUsers;
	}
	
	public void addUser(User user){
		if(user != null){
			for (User temp : users) {
				if(temp.getLoginName().trim().equals(user.getLoginName().trim())){
					removeUserById(user.getLoginName());
					break;
				}
			}
			users.add(user);
		}
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	public void removeUserById(String name){
		ArrayList<User> delList = new ArrayList<User>();
		for (User user : users) {
			if(user.getLoginName().trim().equals(name)){
				delList.add(user);
			}
		}
		if(delList.size()>0)
			users.removeAll(delList);
	}
}
