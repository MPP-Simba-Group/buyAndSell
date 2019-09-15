package com.mpp.buyAndSell.core.post.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
public class PostControllerTest {

	private MockMvc mockMvc;
	@InjectMocks
	private PostController postController;
	@Before
	public void setUp() throws Exception{
		mockMvc= MockMvcBuilders.standaloneSetup(postController).build();
	}
	@Test
	public void index() {

	}

	@Test
	public void show() {
	}

	@Test
	public void search() {
	}

	@Test
	public void addPost() {
	}

	@Test
	public void update() {
	}

	@Test
	public void delete() {
	}

	@Test
	public void getPostService() {
	}

	@Test
	public void setPostService() {
	}
}