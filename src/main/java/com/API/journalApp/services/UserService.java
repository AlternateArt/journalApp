package com.API.journalApp.services;

import com.API.journalApp.entity.Users;
import com.API.journalApp.repository.UserRepository;
//import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository; //injecting repository in this class


    public void saveEntry(Users user){
            userRepository.save(user);
    }

    public List<Users> getAll(){
        return userRepository.findAll();
    }

    public Optional<Users> findById(ObjectId id){
        return userRepository.findById(id);
    }
    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }
    public Users findByUserName(String userName){
        return  userRepository.findByUserName(userName);
    }

}
