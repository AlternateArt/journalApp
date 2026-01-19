package com.API.journalApp.controller;

import com.API.journalApp.entity.Users;
import com.API.journalApp.repository.UserRepository;
import com.API.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
   private UserService userService;

    @GetMapping
    public List<Users> getAllUsers(){
        return userService.getAll();
    }
    @PostMapping
    public void createUser(@RequestBody Users user){
        userService.saveEntry(user);
    }
    @PutMapping ("/{userName}")
    public ResponseEntity<?> userUpdate(@RequestBody Users user, @PathVariable String userName) {
        Users userInDb = userService.findByUserName(userName);
        if (userInDb != null) {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
        userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

