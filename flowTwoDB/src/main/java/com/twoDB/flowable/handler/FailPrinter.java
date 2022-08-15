package com.twoDB.flowable.handler;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@NoArgsConstructor
@Data
public class FailPrinter implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) {
		String userOfProcess =  (String) execution.getVariable("user");
		log.info("Failed Auto Message ! {}",userOfProcess);

	}

}
