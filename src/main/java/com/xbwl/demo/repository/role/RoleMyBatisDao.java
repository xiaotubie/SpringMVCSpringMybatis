package com.xbwl.demo.repository.role;

import java.util.List;
import java.util.Map;

import com.xbwl.demo.entity.role.Role;
import com.xbwl.demo.repository.MyBatisRepository;

@MyBatisRepository
public interface RoleMyBatisDao {

    List<Role> findAllRoles(String flag);
    
    Role findByCode(String code);
    
    List<Role> findAll(Map<String, String> sortTypes);
    
    Role findOne(Long id);
        
    void save(Role role);
    
    void update(Role role);
    
    void delete(List<Long> ids);
    
    void recover(Map<String, Object> map);
    
}
