package com.example.UserService.model.repository;

import com.example.UserService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
