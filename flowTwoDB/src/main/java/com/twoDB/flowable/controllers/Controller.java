package com.twoDB.flowable.controllers;

import java.util.ArrayList;
import java.util.List;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twoDB.flowable.repos.UserRepo;
import com.twoDB.flowable.services.UserServiceImpl;






@RestController
@RequestMapping("/test")
public class Controller {
	@Autowired
	UserRepo modelRepo ;
	@Autowired
	UserServiceImpl userServiceImpl ;
	
	//start process instance
	@PostMapping(value="/process")
    public void startProcessInstance(@RequestParam String assignee,@RequestParam String processId) {
		
        userServiceImpl.startProcess(assignee,processId);
    }
	

	//complete task in a process instance
	@PostMapping(value="/completeTask")
    public void completeTask(@RequestParam String taskId) {
        userServiceImpl.completeTask(taskId);
        
    }
	@PostMapping(value="/validateTask")
    public void completeTask(@RequestParam String  taskId,@RequestParam Boolean isValid) {
        userServiceImpl.validateTask(taskId, isValid);
        
    }
	 	//get unfinished tasks ?
	    @GetMapping(value="/tasks")
	    public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
	        List<Task> tasks = userServiceImpl.getTasks(assignee);
	        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
	        for (Task task : tasks) {
	            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
	            
	        }
	        return dtos;
	        
	    }
	    
	    @GetMapping(value="/taskByProc")
	    public List<String> getProcTask(@RequestParam String procs) {
	        
	    	userServiceImpl.getCurrentTask(procs);
	    	
	    	return userServiceImpl.getCurrentTask(procs);
	        
	    }
	    
	  //get unfinished tasks ?
	    @GetMapping(value="/tasksAdmin")
	    public List<TaskRepresentation> getAdminTasks() {
	        List<Task> tasks = userServiceImpl.getTasksForAdmin();
	        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
	        for (Task task : tasks) {
	            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
	        }
	        return dtos;
	    }

	    static class TaskRepresentation {

	        private String id;
	        private String name;

	        public TaskRepresentation(String id, String name) {
	            this.id = id;
	            this.name = name;
	        }

	        public String getId() {
	            return id;
	        }
	        public void setId(String id) {
	            this.id = id;
	        }
	        public String getName() {
	            return name;
	        }
	        public void setName(String name) {
	            this.name = name;
	        }

	    }

	    static class StartProcessRepresentation {

	        private String assignee;

	        public String getAssignee() {
	            return assignee;
	        }

	        public void setAssignee(String assignee) {
	            this.assignee = assignee;
	        }
	    }
}
