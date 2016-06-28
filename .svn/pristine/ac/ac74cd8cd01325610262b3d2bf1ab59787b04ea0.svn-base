package com.xbwl.demo.repository;

import java.util.List;

import com.xbwl.demo.entity.User;

@MyBatisRepository
public interface UserMyBatisDao {
    
    List<User> findAll();
    
    User findOne(Long id);
    
    User findByLoginName(String loginName);
    
    void save(User user);
    
    void update(User user);
    
    void delete(Long id);
}
