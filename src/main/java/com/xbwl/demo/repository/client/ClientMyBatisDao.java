package com.xbwl.demo.repository.client;

import java.util.List;
import java.util.Map;

import com.xbwl.demo.entity.client.Client;
import com.xbwl.demo.repository.MyBatisRepository;

@MyBatisRepository
public interface ClientMyBatisDao {

    void save(Client client);// 创建客户端
    
    void update(Client client);// 更新客户端
    
    void delete(List<Long> ids);// 删除客户端
    
    Client findOne(Long id);// 根据 id 查找客户端
    
    List<Client> findAll(Map<String, String> sortTypes);// 查找所有
    
    Client findByClientId(String clientId);// 根据客户端 id 查找客户端
    
    Client findByClientSecret(String clientSecret);//根据客户端安全 KEY 查找客户端
    
}
