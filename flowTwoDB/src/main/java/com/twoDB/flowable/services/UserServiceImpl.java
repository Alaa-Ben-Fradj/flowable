package com.twoDB.flowable.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.flowable.engine.IdentityService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceBuilder;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twoDB.flowable.models.Person;
import com.twoDB.flowable.repos.PersonRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UserServiceImpl {
	@Autowired
	RepositoryService repositoryService ;
	@Autowired
	TaskService taskService ;
	@Autowired
	RuntimeService runtimeService ;
	@Autowired
	ProcessEngine processEngine;
	@Autowired
	IdentityService identityService ;
	
	
    @Autowired
    private PersonRepository personRepository;
    
    
    
    
    public void startProcess(String assignee,String processId) {
    	Map<String, Object> variables = new HashMap<String, Object>();
    	
    	Person person = personRepository.findByUsername(assignee);
    	variables.put("user", person.getUsername());
    	log.info("starting phase completed");
    	ProcessInstanceBuilder test=runtimeService.createProcessInstanceBuilder();
    	test.predefineProcessInstanceId(processId)
    	.variables(variables)
    	.processDefinitionKey("modalDesign")
    	.start();
    	//runtimeService.startProcessInstanceByKey("modalDesign",variables);
    	
    }

    public List<String> getCurrentTask (String processId) {
    	
    	// in your case only one execution is selected
        List<Execution> executions = runtimeService.createExecutionQuery().onlyChildExecutions().processInstanceId(processId).list();
// activityId is id from the modeler. Can be any wait state (user task, async service task, receive task.....)
        List<String> activityIds = executions.stream().map(Execution::getActivityId).collect(Collectors.toList());
    	
    	return activityIds ;
    }
    
    
    public void completeTask (String taskId) {
        taskService.complete(taskId);
    }
   
    public void validateTask (String taskId ,boolean isOk) {
    	Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("failed", isOk);
        taskService.complete(taskId, variables);
    }
    

    public List<Task> getTasks(String assignee) {
    	log.info("getting tasks phase is completed ");
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }
    public List<Task> getTasksForAdmin() {
    	log.info("getting admin tasks ");
        return taskService.createTaskQuery().taskCandidateGroup("admin").list();
    }

    
    public void createDemoUsers() {
    	
        if (personRepository.findAll().size() == 0) {
        	Group adminsGroup = identityService.newGroup("admin");
            identityService.saveGroup(adminsGroup);
            personRepository.save(new Person("alaabf", "alaa", "benfradj", new Date()));
            User jbarrez = identityService.newUser("alaabf");
            identityService.saveUser(jbarrez);
            identityService.createMembership("alaabf", "admin");
            personRepository.save(new Person("wiss", "wissem", "bhk", new Date()));
        }
        
        
        
        
        
    }

	
	
	

}
