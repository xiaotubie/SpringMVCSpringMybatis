package com.xbwl.demo.repository.resource;

import java.util.List;
import java.util.Map;

import com.xbwl.demo.entity.resource.Resource;
import com.xbwl.demo.repository.MyBatisRepository;

@MyBatisRepository
public interface ResourceMyBatisDao {

    List<Resource> findAllResources();
    
    List<Resource> findAllResourcesNoBtn();
    
    List<Resource> findAllResourcesByAdmin();
    
    List<Resource> findAllResourcesByRole(Long roleId);
    
    List<Resource> findAllResourcesByParenIdAndRole(Long parentId);
    
    List<Resource> findAllResourcesByParenIdAndAdmin(Long parentId);
    
    List<Resource> findAll(Map<String, String> sortTypes);
    
    Resource findOne(Long id);
        
    void save(Resource resource);
    
    void update(Resource resource);
    
    void delete(List<Long> ids);
    
    void recover(Map<String, String> map);
}
