package com.purnabhu.sales.controllers;

import com.purnabhu.sales.entities.Roles;
import com.purnabhu.sales.entities.User;
import com.purnabhu.sales.repository.RoleRepository;
import com.purnabhu.sales.response.ResponseEntityObject;
import com.purnabhu.sales.services.UserService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ResponseEntityObject responseEntityObject;

    @PostMapping("/addUser")
    private ResponseEntity createUser(@Valid @RequestBody User user){
        if (userService.existsByUsername(user.getUserName())) {
            return responseEntityObject.generateResponse("Error: Username is already exist!",HttpStatus.NOT_ACCEPTABLE,"");
        }
        if (userService.existsByuserEmailId(user.getUserEmailId())) {
            return responseEntityObject.generateResponse("Error: Email is already in use!",HttpStatus.NOT_ACCEPTABLE,"");
        }
        User createdUser = null;
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            Set<Roles> roles = new HashSet<>();
            Roles userRole;
            try {
                userRole = roleRepository.findByRoleId(user.getRoles().iterator().next().getRoleId())
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            }catch (RuntimeException runtimeException){
                return responseEntityObject.generateResponse(runtimeException.getMessage(),HttpStatus.NOT_ACCEPTABLE,"");
            }
            roles.add(userRole);
            user.setRoles(roles);
            logger.info("User creation start....");
            createdUser = userService.createUser(user);
        }catch (Exception exception){
            return responseEntityObject.generateResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE,"");
        }
        return responseEntityObject.generateResponse("User created successfully",HttpStatus.OK,createdUser);
    }

    @PutMapping("/updateUser")
    private ResponseEntity  updateUser(@Valid @RequestBody User user){
        if (userService.existsByUsername(user.getUserName())) {
            return responseEntityObject.generateResponse("Error: Username is already exist!",HttpStatus.NOT_ACCEPTABLE,"");
        }

        if (userService.existsByuserEmailId(user.getUserEmailId())) {
            return responseEntityObject.generateResponse("Error: Email is already in use!",HttpStatus.NOT_ACCEPTABLE,"");
        }
        User createdUser = null;
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            logger.info("User updation start....");
            createdUser = userService.updateUser(user);
        }catch (Exception exception){
            return responseEntityObject.generateResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE,"");
        }
        return responseEntityObject.generateResponse("User updated successfully",HttpStatus.OK,createdUser);
    }
    @GetMapping("/resetPassword")
    private ResponseEntity<List<User>> resetPassword(){
        logger.info("Fetch All Users....");
        List<User> usersList = userService.getAllUser();
        return new ResponseEntity<List<User>>(usersList,HttpStatus.OK);
    }
    @GetMapping("/searchUser/{userName}")
    private ResponseEntity<Object> searchUser(@PathVariable String userName){
        logger.info("Search User by name and id....");
        Optional<User> searchUser = null;
        try {
            searchUser = userService.searchUser(userName);
            if(searchUser.isEmpty())
                return responseEntityObject.generateResponse("User Not Found",HttpStatus.OK,"");
        }catch (Exception exception){
            return responseEntityObject.generateResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE,"");
        }
        return responseEntityObject.generateResponse("User search successfully",HttpStatus.OK,searchUser);
    }
    @GetMapping("/getAllUsers")
    private ResponseEntity<Object> getAllUsers(){
        logger.info("Fetch All Users....");
        List<User> usersList = null;
        try {
            usersList = userService.getAllUser();
            if(usersList.isEmpty())
                responseEntityObject.generateResponse("Users Not Available",HttpStatus.OK,"");
        }catch (Exception exception){
            return responseEntityObject.generateResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE,"");
        }
        return responseEntityObject.generateResponse("All Users",HttpStatus.OK,usersList);
    }

    @PostMapping("/deleteUser/{userId}")
    private ResponseEntity<Object> deleteUser(@PathVariable Long userId){
        logger.info("Delete User....");
        try {
             userService.deleteUser(userId);
        }catch (Exception exception){
            return responseEntityObject.generateResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE,"");
        }
        return responseEntityObject.generateResponse("User deleted successfully",HttpStatus.OK,"");
    }
}
