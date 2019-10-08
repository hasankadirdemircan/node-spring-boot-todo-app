package com.hkdemircan.todoapp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.hkdemircan.todoapp.base.BaseIntegrationTest;
import com.hkdemircan.todoapp.dto.UserDto;
import com.hkdemircan.todoapp.model.User;
import com.hkdemircan.todoapp.repository.UserRepository;
import com.hkdemircan.todoapp.request.UserRequest;
import com.hkdemircan.todoapp.util.JsonUtil;

public class UserControllerIntegrationTest extends BaseIntegrationTest {

	@Autowired
	UserRepository userRepository;
	
	private String uri = "/user";
	

	@After
	public void resetDb() {
		userRepository.deleteAll();
	}
	
	@Test
	public void createUserTest() throws IOException, Exception {
		UserRequest req = new UserRequest();
		UserDto dto = new UserDto();
		
		dto.setCreateBy("test");
		dto.setCreateDate(new Date());
		dto.setId(1);
		dto.setRole("admin");
		dto.setUsername("test");
		dto.setPassword("test");
		
		req.setUser(dto);
		
		mvc.perform(post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(req)))
				.andExpect(status().isOk());
		
		List<User> userList = userRepository.findAll();
		assertThat(userList.size(),equalTo(1));
		assertThat(userList.get(0).getId(), equalTo(1));
	}
}
