package com.twoDB.flowable.models;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

	@Id
	private String id ;
	private String name ;
	private String lastName ;
	
	
	
}
