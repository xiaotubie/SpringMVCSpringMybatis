package com.xbwl.demo.repository.people;

import java.util.List;

import com.xbwl.demo.entity.people.People;
import com.xbwl.demo.repository.MyBatisRepository;

@MyBatisRepository
public interface PeopleMybatisDao {
    
    People findById(Long id);
    
    void save(People people);
    
    void update(People people);
    
    void delete(Long id);
    
    List<People> findAll();
}
