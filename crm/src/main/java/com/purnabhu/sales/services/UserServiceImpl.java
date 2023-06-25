package com.purnabhu.sales.services;

import com.purnabhu.sales.entities.Roles;
import com.purnabhu.sales.entities.User;
import com.purnabhu.sales.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    UserRepository userRepository;
    @Override
    public User createUser(User user) {
        logger.info("Creating user...");
        Roles roles = new Roles();
        roles.setRoleId(user.getUserId());
        roles.setRoleName("ROLE_ADMIN");
        roles.setRoleDesc("Admin");
        HashSet roleSet = new HashSet();
        roleSet.add(roles);
        user.setRoles(roleSet);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User searchUser(String userId, String userName) {
        return userRepository.findByUserIdOrUserName(userId, userName);
    }

    @Override
    public List<User> getAllUser() {
        logger.info("Fetching all users...");
        return userRepository.findAll();
    }

    @Override
    public boolean existsByUsername(String userName) {
        return userRepository.existsByUserName(userName);
    }

    @Override
    public boolean existsByuserEmailId(String userEmailId) {
        return userRepository.existsByuserEmailId(userEmailId);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }
}
