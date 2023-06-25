package com.purnabhu.sales.controllers;

import com.purnabhu.sales.entities.User;
import com.purnabhu.sales.response.ResponseEntityObject;
import com.purnabhu.sales.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    private ResponseEntityObject  createUser(@RequestBody User user){
        ResponseEntityObject responseEntityObject = new ResponseEntityObject();
        if (userService.existsByUsername(user.getUserName())) {
            responseEntityObject.setResponseMessage("Error: Username is already taken!");
            return responseEntityObject;
        }

        if (userService.existsByuserEmailId(user.getUserEmailId())) {
            responseEntityObject.setResponseMessage("Error: Email is already in use!");
            return responseEntityObject;
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        logger.info("User creation start....");
        User createdUser = userService.createUser(user);
        responseEntityObject.setResponseCode(200);
        responseEntityObject.setResponseMessage("User created successfully");
        return responseEntityObject;
    }

    @PutMapping("/updateUser")
    private ResponseEntityObject  updateUser(@RequestBody User user){
        ResponseEntityObject responseEntityObject = new ResponseEntityObject();
        if (userService.existsByUsername(user.getUserName())) {
            responseEntityObject.setResponseMessage("Error: Username is already taken!");
            return responseEntityObject;
        }

        if (userService.existsByuserEmailId(user.getUserEmailId())) {
            responseEntityObject.setResponseMessage("Error: Email is already in use!");
            return responseEntityObject;
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        logger.info("User updation start....");
        User createdUser = userService.updateUser(user);
        responseEntityObject.setResponseCode(200);
        responseEntityObject.setResponseMessage("User updated successfully");
        return responseEntityObject;
    }
    @GetMapping("/resetPassword")
    private ResponseEntity<List<User>> resetPassword(){
        logger.info("Fetch All Users....");
        List<User> usersList = userService.getAllUser();
        return new ResponseEntity<List<User>>(usersList,HttpStatus.OK);
    }

    @GetMapping("/searchUser/{userId}/{name}")
    private ResponseEntity<User> searchUser(@PathVariable Map<String, String> userSearch){
        String userId = userSearch.get("userId");
        String userName = userSearch.get("name");
        logger.info("Search User by name and id....");
        User searchUser = userService.searchUser(userId, userName);
        return new ResponseEntity<User>(searchUser,HttpStatus.OK);
    }
    @GetMapping("/getAllUsers")
    private ResponseEntity<List<User>> getAllUsers(){
        logger.info("Fetch All Users....");
        List<User> usersList = userService.getAllUser();
        return new ResponseEntity<List<User>>(usersList,HttpStatus.OK);
    }
}
