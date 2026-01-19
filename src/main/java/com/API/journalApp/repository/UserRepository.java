package com.API.journalApp.repository;

import com.API.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends MongoRepository<Users, ObjectId> {
            Users findByUserName (String username);

}
