package com.purnabhu.sales.repository;

import com.purnabhu.sales.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUserName(String userName);

    Boolean existsByUserName(String userName);

    Boolean existsByuserEmailId(String email);

    User findByUserIdOrUserName(String userId, String user);

}
