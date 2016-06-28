package com.xbwl.demo.service.role;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xbwl.demo.entity.role.Role;
import com.xbwl.demo.repository.role.RoleMyBatisDao;

@Component
@Transactional
public class RoleService {

    @Autowired
    private RoleMyBatisDao roleMyBatisDao;
    
    public List<Role> findAllRoles(String flag){
        return roleMyBatisDao.findAllRoles(flag);
    }
    
    public Role findByCode(String code) {
        return roleMyBatisDao.findByCode(code);
    }
    
    public Role findById(Long id) {
        return roleMyBatisDao.findOne(id);
    }

    public void save(Role role){
        roleMyBatisDao.save(role);
    }

    public void update(Role role){
        roleMyBatisDao.update(role);
    }

    public void delete(List<Long> ids){
        roleMyBatisDao.delete(ids);
    }
    
    public void recover(Map<String, Object> map){
        roleMyBatisDao.recover(map);
    }
    
    /**
     * 选用了Mybatis的分页插件
     */
    public PageInfo<Role> findRole(int pageNumber, int pageSize, Map<String, String> sortTypes){
        PageHelper.startPage(pageNumber, pageSize);
        List<Role> roles = roleMyBatisDao.findAll(sortTypes);
       
        PageInfo<Role> pageinfo = new PageInfo<Role>(roles);
        return pageinfo;
    }
}
