package com.xbwl.demo.repository;

import java.util.List;
import java.util.Map;

import com.xbwl.demo.entity.Task;

@MyBatisRepository
public interface TaskMyBatisDao {
    
    Task findOne(Long id);
    
    void save(Task task);
    
    void update(Task task);
    
    void delete(Long id);
    
    List<Task> findAll();
    
    List<Task> findAllTask(Map<String,Object> map);
    
    void deleteByUserId(Long id);
}
