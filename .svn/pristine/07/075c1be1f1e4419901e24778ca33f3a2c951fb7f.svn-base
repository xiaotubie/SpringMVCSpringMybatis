package com.xbwl.demo.service.resource;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xbwl.demo.entity.resource.Resource;
import com.xbwl.demo.repository.resource.ResourceMyBatisDao;

@Component
@Transactional
public class ResourceService {

    @Autowired
    private ResourceMyBatisDao resourceMyBatisDao;
    
    public List<Resource> findAllResources(){
        List<Resource> resources = resourceMyBatisDao.findAllResources();
        resources = sortList(resources);
        return resources;
    }
    
    public List<Resource> findAllResourcesNoBtn(){
        List<Resource> resources = resourceMyBatisDao.findAllResourcesNoBtn();
        resources = sortList(resources);
        return resources;
    }
    
    public List<Resource> findAllResourcesByAdmin(){
        List<Resource> resources = resourceMyBatisDao.findAllResourcesByAdmin();
        resources = sortList(resources);
        resources = findChildAdmin(resources);
        return resources;
    }
    
    public List<Resource> findAllResourcesByRole(Long roleId){
        List<Resource> resources = resourceMyBatisDao.findAllResourcesByRole(roleId);
        resources = sortList(resources);
        resources = findChildByRole(resources);
        return resources;
    }
    
    public List<Resource> sortList(List<Resource> resources){
        if(resources != null && resources.size() > 0){
            for(int i = 0; i < resources.size()-1; i++) {
                for(int j = 1; j < resources.size()-i; j++) {
                    Resource resource;
                    if((resources.get(j-1)).getResSort() > resources.get(j).getResSort()){ 
                        resource = resources.get(j-1);
                        resources.set((j-1),resources.get(j));
                        resources.set(j,resource);
                    }else if((resources.get(j-1)).getResSort() == resources.get(j).getResSort()){ 
                         if((resources.get(j-1)).getId() > resources.get(j).getId()){ 
                             resource = resources.get(j-1);
                                resources.set((j-1),resources.get(j));
                                resources.set(j,resource);
                            }
                    }
                }
            }
        }
        return resources;
    }
    
    public List<Resource> findChildByRole(List<Resource> resources){
        if(resources != null && resources.size() > 0){
            for (int i = 0; i < resources.size(); i++) {
                Resource resource = resources.get(i);
                Long id = resource.getId();
                List<Resource> childMenu = resourceMyBatisDao.findAllResourcesByParenIdAndRole(id);
                if(childMenu != null && childMenu.size() > 0){
                    resource.setChildMenu(childMenu);
                    for (int j = 0; j < childMenu.size(); j++) {
                        Resource resource2 = childMenu.get(j);
                        Long id2 = resource2.getId();
                        List<Resource> childMenu2 = resourceMyBatisDao.findAllResourcesByParenIdAndRole(id2);
                        if(childMenu2 != null && childMenu2.size() > 0){
                            resource2.setChildMenu(childMenu2);
                            for (int k = 0; k < childMenu2.size(); k++) {
                                Resource resource3 = childMenu.get(j);
                                Long id3 = resource3.getId();
                                List<Resource> childMenu3 = resourceMyBatisDao.findAllResourcesByParenIdAndRole(id3);
                                if(childMenu3 != null && childMenu3.size() > 0){
                                    resource3.setChildMenu(childMenu3);
                                }
                            }
                        }
                    }
                }
            }
        }
        return resources;
    }
    
    public List<Resource> findChildAdmin(List<Resource> resources){
        if(resources != null && resources.size() > 0){
            for (int i = 0; i < resources.size(); i++) {
                Resource resource = resources.get(i);
                Long id = resource.getId();
                List<Resource> childMenu = resourceMyBatisDao.findAllResourcesByParenIdAndAdmin(id);
                if(childMenu != null && childMenu.size() > 0){
                    resource.setChildMenu(childMenu);
                    for (int j = 0; j < childMenu.size(); j++) {
                        Resource resource2 = childMenu.get(j);
                        Long id2 = resource2.getId();
                        List<Resource> childMenu2 = resourceMyBatisDao.findAllResourcesByParenIdAndAdmin(id2);
                        if(childMenu2 != null && childMenu2.size() > 0){
                            resource2.setChildMenu(childMenu2);
                            for (int k = 0; k < childMenu2.size(); k++) {
                                Resource resource3 = childMenu.get(j);
                                Long id3 = resource3.getId();
                                List<Resource> childMenu3 = resourceMyBatisDao.findAllResourcesByParenIdAndAdmin(id3);
                                if(childMenu3 != null && childMenu3.size() > 0){
                                    resource3.setChildMenu(childMenu3);
                                }
                            }
                        }
                    }
                }
            }
        }
        return resources;
    }
    
    public Resource findById(Long id) {
        return resourceMyBatisDao.findOne(id);
    }

    public void save(Resource resource){
    	resourceMyBatisDao.save(resource);
    }

    public void update(Resource resource){
    	resourceMyBatisDao.update(resource);
    }

    public void delete(List<Long> ids){
    	resourceMyBatisDao.delete(ids);
    }
    
    public void recover(Map<String, String> map){
    	resourceMyBatisDao.recover(map);
    }
    
    /**
     * 选用了Mybatis的分页插件
     */
    public PageInfo<Resource> findResource(int pageNumber, int pageSize, Map<String, String> sortTypes){
        PageHelper.startPage(pageNumber, pageSize);
        List<Resource> resources = resourceMyBatisDao.findAll(sortTypes);
        PageInfo<Resource> pageinfo = new PageInfo<Resource>(resources);
        return pageinfo;
    }
}
