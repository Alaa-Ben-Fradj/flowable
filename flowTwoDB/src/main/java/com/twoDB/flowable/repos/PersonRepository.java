package com.twoDB.flowable.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.twoDB.flowable.models.Person;
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	public Person findByUsername (String name );
}
