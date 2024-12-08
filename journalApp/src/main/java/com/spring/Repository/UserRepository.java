package com.spring.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.Entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId>{

	User findByUserName(String userName);
}
