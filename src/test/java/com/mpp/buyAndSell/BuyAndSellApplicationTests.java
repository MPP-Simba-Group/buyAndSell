package com.mpp.buyAndSell;

import com.mpp.buyAndSell.core.FunctionalProgramming.Operations;
import com.mpp.buyAndSell.core.user.entity.User;
import com.mpp.buyAndSell.core.user.repo.UserRepo;
import com.mpp.buyAndSell.core.user.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyAndSellApplicationTests {

	@Mock
	UserService userService;

	@Mock
	private UserRepo userRepo;

//	@Mock
	@Autowired
	private Operations operations;

	@Test
	public void contextLoads() {
		when(getUserService().getAllUsers()).thenReturn(getUsersList());
		assertEquals(5, getOperations().blockedUsers.apply(getUsersList()).size());
	}

	public UserRepo getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Operations getOperations() {
		return operations;
	}

	public void setOperations(Operations operations) {
		this.operations = operations;
	}

	public List<User> getUsersList(){
		List<User> users = new ArrayList<>();
		for(int i = 0; i<10; i++){
			User user = new User(i, "firstName"+i, "lastName"+i, "email"+i+"@gmail.com","123"+i, i%2==0);
			users.add(user);
		}
		return users;
	}
}
