package com.hkdemircan.todoapp.repository;

import com.hkdemircan.todoapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
