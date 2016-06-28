package com.xbwl.demo.repository.permission;

import java.util.List;
import java.util.Map;

import com.xbwl.demo.entity.permission.Permission;
import com.xbwl.demo.repository.MyBatisRepository;

@MyBatisRepository
public interface PermissionMyBatisDao {

    List<Permission> findByRoleId(Long roleId);
    
    List<Permission> findAllByRoleId(Long roleId);
    
    List<Permission> findBtnByRoleId(Long roleId);
    
    List<Permission> findByRoleIdAndResId(Long roleId);
    
    List<Permission> findAll(Map<String, String> sortTypes);
    
    Permission findOne(Long id);
    
    void save(Permission permission);
    
    void deleteByRoleId(Long roleId);
    
    void deleteByResId(Long resId);
    
    void deleteByRoleIds(List<Long> ids);
    
    void deleteByResIds(List<Long> ids);
}
