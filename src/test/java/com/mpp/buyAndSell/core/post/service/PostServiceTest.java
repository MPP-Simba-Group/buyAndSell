package com.mpp.buyAndSell.core.post.service;

import com.mpp.buyAndSell.core.post.entity.Post;
import com.mpp.buyAndSell.core.post.repository.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
public class PostServiceTest {
	@TestConfiguration
	static class PostServiceTestContextConfiguration{
		@Bean
		public PostService postService(){
			return new PostService();
		}
	}
	@Autowired
	private PostService postService;
	@MockBean
	private PostRepository postRepository;
	@Before
	public void setUp(){
		List<Post> post= new ArrayList<>();
		post.add(new Post("Google X","excellent smart phone", 577.0));
		post.add(new Post("iPhone X","excellent smart phone", 877.0));
		Mockito.when(postRepository.findAll()).thenReturn(post);
		Mockito.when(postRepository.findById(0l)).thenReturn(java.util.Optional.ofNullable(post.get(0)));
		Mockito.when(postRepository.findById(1l)).thenReturn(java.util.Optional.ofNullable(post.get(1)));
	}
	@Test
	public void findAll() {
		List<Post> posts=postRepository.findAll();
		assertThat(posts.get(0).getItemName()).isEqualToIgnoringCase("Google X");
		assertThat(posts.get(1).getItemName()).isEqualToIgnoringCase("iPhone X");

	}

	@Test
	public void getPost() {
	}

	/*@Test
	public void addPost() {
	}

	@Test
	public void updatePost() {
	}*/
}