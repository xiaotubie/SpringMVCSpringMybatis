package com.xbwl.demo.service.task;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.xbwl.demo.entity.Task;
import com.xbwl.demo.log.SystemServiceLog;
import com.xbwl.demo.repository.TaskMyBatisDao;

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class TaskService {

	private TaskMyBatisDao taskDao;

	public Task getTask(Long id) {
		return taskDao.findOne(id);
	}

	public void saveTask(Task entity) {
		taskDao.save(entity);
	}
	
	public void updateTask(Task task) {
	    taskDao.update(task);
	}
	
	@SystemServiceLog
	public void deleteTask(Long id) {
		taskDao.delete(id);
	}
	
	
	public List<Task> getAllTask() {
		return (List<Task>) taskDao.findAll();
	}
	
	@SystemServiceLog(description="查询任务列表")
	public PageInfo<Task> getUserTask(Long userId, String title, int pageNumber, int pageSize,String sortType) {
//		new Throwable("出错了");
	    Map<String,Object> map = Maps.newHashMap();
		map.put("userId", userId);
		map.put("title", title);
		PageHelper.startPage(pageNumber, pageSize);
		List<Task> tasks = taskDao.findAllTask(map);
		PageInfo<Task> page = new PageInfo<Task>(tasks);
		return page;
	}

	@Autowired
	public void setTaskDao(TaskMyBatisDao taskDao) {
		this.taskDao = taskDao;
	}
}
