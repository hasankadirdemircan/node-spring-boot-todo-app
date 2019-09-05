package com.hkdemircan.todoapp.repository;

import com.hkdemircan.todoapp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer>{

}