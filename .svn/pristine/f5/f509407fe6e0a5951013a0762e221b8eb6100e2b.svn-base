package com.xbwl.demo.service.people;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xbwl.demo.entity.people.People;
import com.xbwl.demo.repository.people.PeopleMybatisDao;

@Component
@Transactional
public class PeopleService {

    @Autowired
    private PeopleMybatisDao peopleDao;

    public People findById(Long id) {
        return peopleDao.findById(id);
    }

    public void save(People people){
         peopleDao.save(people);
    }

    public void update(People people){
        peopleDao.update(people);
    }

    public void delete(Long id){
        peopleDao.delete(id);
    }
    
    /**
     * 选用了Mybatis的分页插件
     * 中国哥们编写的，开源于github，32个赞
     */
    public PageInfo<People> findPeople(int pageNumber, int pageSize){
        PageHelper.startPage(pageNumber, pageSize);
        List<People> peoples = peopleDao.findAll();
       
        PageInfo<People> pageinfo = new PageInfo<People>(peoples);
        return pageinfo;
    }

}
