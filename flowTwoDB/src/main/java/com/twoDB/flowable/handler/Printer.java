package com.twoDB.flowable.handler;

import java.util.List;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.twoDB.flowable.services.UserServiceImpl;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@Slf4j
@Component("printer")
public class Printer implements JavaDelegate {
	@Autowired
	 UserServiceImpl ex ;
	
	
	@Override
	public void execute(DelegateExecution execution) {
		List<String> currentTask = ex.getCurrentTask(execution.getProcessInstanceId());
		currentTask.stream().forEach(t->log.info(t));
		
		log.info("success !!!!!!!!!");
		}
	
	

}
