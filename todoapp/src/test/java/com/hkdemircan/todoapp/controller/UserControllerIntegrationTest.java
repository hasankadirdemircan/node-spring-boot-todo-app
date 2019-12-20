package com.hkdemircan.todoapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.hkdemircan.todoapp.dto.UserDto;
import com.hkdemircan.todoapp.model.Login;
import com.hkdemircan.todoapp.model.Request;
import com.hkdemircan.todoapp.model.Todo;
import com.hkdemircan.todoapp.repository.TodoRepository;
import com.hkdemircan.todoapp.request.UserRequest;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.hkdemircan.todoapp.base.BaseIntegrationTest;
import com.hkdemircan.todoapp.model.User;
import com.hkdemircan.todoapp.repository.UserRepository;
import com.hkdemircan.todoapp.util.JsonUtil;
import org.springframework.test.web.servlet.MvcResult;

public class UserControllerIntegrationTest extends BaseIntegrationTest {

	@Autowired
	UserRepository userRepository;

	@Autowired
	TodoRepository todoRepository;
	
	private String userUri = "/user";
	private String todoUri = "/todo";

	@After
	public void resetDb() {
		userRepository.deleteAll();
	}

	@Test
	public void shouldSaveUser() {
		Request req = new Request();

		try {
			mvc.perform(post(userUri)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.content(JsonUtil.toJson(req.userSaveRequest())))
					.andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<User> userList = userRepository.findAll();
		assertThat(userList.size(),equalTo(1));
		//assertThat(userList.get(0).getId(), equalTo(1));
	}


	public String shouldGiveToken() {
		shouldSaveUser();
		Login login = new Login("test", "test");

		MvcResult result = null;
		try {
			result = mvc.perform(post("/login")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.content(JsonUtil.toJson(login)))
					.andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String bearerToken = result.getResponse().getHeader("Authorization").toString();
		return bearerToken;
	}

	@Test
	public void shouldSaveTodo(){
		String jwt = shouldGiveToken();
		Request request = new Request();

		try {
			mvc.perform(post(todoUri)
					.header("Authorization", jwt)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.content(JsonUtil.toJson(request.todoRequest())))
					.andExpect(status().isOk());
			List<Todo> todoList = todoRepository.findAll();
			assertThat(todoList.size(),equalTo(1));
			//assertThat(todoList.get(0).getId(), equalTo(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
