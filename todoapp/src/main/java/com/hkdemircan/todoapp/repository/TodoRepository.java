package com.hkdemircan.todoapp.repository;

import com.hkdemircan.todoapp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer>{
    List<Todo> findAllByCreateByAndActive(String username, String status);
}