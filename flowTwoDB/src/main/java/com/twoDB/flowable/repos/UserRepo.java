package com.twoDB.flowable.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.twoDB.flowable.models.User;
@Repository
public interface UserRepo extends MongoRepository<User, String>{

}
