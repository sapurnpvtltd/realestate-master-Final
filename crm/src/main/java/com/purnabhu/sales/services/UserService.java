package com.purnabhu.sales.services;

import com.purnabhu.sales.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {
    public User createUser(User user);
    public User updateUser(User user);
    public Optional<User> searchUser(String userName);
    public List<User> getAllUser();
    public boolean existsByUsername(String username);
    public boolean existsByuserEmailId(String userEmailId);

    public void deleteUser(Long userId);
}
