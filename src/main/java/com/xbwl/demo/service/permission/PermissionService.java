package com.xbwl.demo.service.permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.mapper.JsonMapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xbwl.demo.entity.permission.Permission;
import com.xbwl.demo.repository.permission.PermissionMyBatisDao;

@Component
@Transactional
public class PermissionService {

    @Autowired
    private PermissionMyBatisDao permissionMyBatisDao;
    
    public void save(Permission permission){
        permissionMyBatisDao.save(permission);
    }
    
    public Permission findOne(Long id){
        return permissionMyBatisDao.findOne(id);
    }
    
    public List<Permission> findByRoleId(Long roleId) {
        return permissionMyBatisDao.findByRoleId(roleId);
    }
    
    public List<Permission> findBtnByRoleId(Long roleId){
        return permissionMyBatisDao.findBtnByRoleId(roleId);
    }
    
    public List<Permission> findAllByRoleId(Long roleId) {
        return permissionMyBatisDao.findAllByRoleId(roleId);
    }
    
    public List<Permission> findByRoleIdAndResId(Long roleId){
        return permissionMyBatisDao.findByRoleIdAndResId(roleId);
    }
    
    public void deleteByRoleId(Long roleId){
        permissionMyBatisDao.deleteByRoleId(roleId);
    }
    
    public void deleteByResId(Long resId){
    	permissionMyBatisDao.deleteByResId(resId);
    }
    
    public void deleteByRoleIds(List<Long> ids){
        permissionMyBatisDao.deleteByRoleIds(ids);
    }
    
    public void deleteByResIds(List<Long> ids){
        permissionMyBatisDao.deleteByResIds(ids);
    }
    
    /**
     * 选用了Mybatis的分页插件
     */
    public PageInfo<Permission> findPermission(int pageNumber, int pageSize, Map<String, String> sortTypes){
        PageHelper.startPage(pageNumber, pageSize);
        List<Permission> permissions = permissionMyBatisDao.findAll(sortTypes);
        Map<String,Object> map= null;
        if(permissions != null && permissions.size() > 0){
            for (int i = 0; i < permissions.size(); i++) {
                Long roleId = permissions.get(i).getRoleId();
                List<Permission> pList = permissionMyBatisDao.findByRoleId(roleId);
                String resName = "";
                List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
                map = new HashMap<String,Object>();
                map.put("resName", "功能菜单");
            	map.put("resId", 0l);
            	list.add(map);
                if(pList != null && pList.size() > 0){
                    for (int j = 0; j < pList.size(); j++) {
                    	map = new HashMap<String,Object>();
                    	map.put("resName", pList.get(j).getResName());
                    	map.put("resId", pList.get(j).getResId());
                    	map.put("resPid", pList.get(j).getResPid());
                    	list.add(map);
                    }
                }
                if(list != null){
                    JsonMapper mapper = new JsonMapper();
						resName =  mapper.toJson(list);
					
                }
                permissions.get(i).setResName(resName);
            }
        }
        PageInfo<Permission> pageinfo = new PageInfo<Permission>(permissions);
        return pageinfo;
    }
}
