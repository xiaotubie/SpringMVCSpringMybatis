package com.xbwl.demo.service.client;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xbwl.demo.entity.client.Client;
import com.xbwl.demo.repository.client.ClientMyBatisDao;

@Component
@Transactional
public class ClientService {
    
    @Autowired
    private ClientMyBatisDao clientMyBatisDao;
    
    // 创建客户端
    public void createClient(Client client){
        client.setClientId(UUID.randomUUID().toString());
        client.setClientSecret(UUID.randomUUID().toString());
        clientMyBatisDao.save(client);
    }
    // 更新客户端
    public void updateClient(Client client){
        clientMyBatisDao.update(client);
    }
    // 删除客户端
    public void deleteClient(List<Long> ids){
        clientMyBatisDao.delete(ids);
    }
    // 根据 id 查找客户端
    public Client findOne(Long clientId){
        return clientMyBatisDao.findOne(clientId);
    }
    // 根据客户端 id 查找客户端
    public Client findByClientId(String clientId){
        return clientMyBatisDao.findByClientId(clientId);
    }
    //根据客户端安全 KEY 查找客户端
    public Client findByClientSecret(String clientSecret){
        return clientMyBatisDao.findByClientSecret(clientSecret);
    }
    
    /**
     * 选用了Mybatis的分页插件
     */
    public PageInfo<Client> findClient(int pageNumber, int pageSize, Map<String, String> sortTypes){
        PageHelper.startPage(pageNumber, pageSize);
        List<Client> clients = clientMyBatisDao.findAll(sortTypes);
       
        PageInfo<Client> pageinfo = new PageInfo<Client>(clients);
        return pageinfo;
    }
    
}
