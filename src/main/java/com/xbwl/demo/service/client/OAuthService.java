package com.xbwl.demo.service.client;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xbwl.demo.repository.client.ClientMyBatisDao;

@Component
@Transactional
public class OAuthService {

    private Cache<String, String> cache;
    
    @Autowired
    private ClientMyBatisDao clientMyBatisDao;

    @Autowired
    public OAuthService(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("code-cache");
    }
    
    // 添加 auth code
    public void addAuthCode(String authCode, String username){
        cache.put(authCode, username);
    }
    
    // 添加 access token
    public void addAccessToken(String accessToken, String username){
        cache.put(accessToken, username);
    } 
    
    // 验证 auth code 是否有效
    public boolean checkAuthCode(String authCode){
        return cache.get(authCode) != null;
    }
    
    // 验证 access token 是否有效
    public boolean checkAccessToken(String accessToken){
        return cache.get(accessToken) != null;
    }
    
    // 根据 auth code 获取用户名
    public String getUsernameByAuthCode(String authCode){
        return (String)cache.get(authCode);
    }
    
    // 根据 access token 获取用户名
    public String getUsernameByAccessToken(String accessToken){
        return (String)cache.get(accessToken);
    }
    
    //auth code / access token 过期时间
    public long getExpireIn(){
        return 3600L;
    }
    
    // 检查客户端 id 是否存在
    public boolean checkClientId(String clientId){
        return clientMyBatisDao.findByClientId(clientId) != null;
    }
    
    // 坚持客户端安全 KEY 是否存在
    public boolean checkClientSecret(String clientSecret){
        return clientMyBatisDao.findByClientSecret(clientSecret) != null;
    }

}

