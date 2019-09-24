package com.mpp.buyAndSell;

import com.mpp.buyAndSell.core.FunctionalProgramming.Operations;
import com.mpp.buyAndSell.core.comment.entity.Comment;
import com.mpp.buyAndSell.core.comment.service.CommentService;
import com.mpp.buyAndSell.core.item.entity.Item;
import com.mpp.buyAndSell.core.item.entity.ItemCategoryEnum;
import com.mpp.buyAndSell.core.item.service.ItemService;
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
	CommentService commentService;


	@Mock
	ItemService itemService;

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

	@Test
	public void testTopCategory() {
		//when(getCommentService().getAllComments()).thenReturn(getCommentsList());
		when(getItemService().findAll()).thenReturn(getItemsList());
		assertEquals(ItemCategoryEnum.CAR, getOperations().topCategory.apply(getItemsList()));
	}

	@Test
    public void testUserComments() {
        when(getCommentService().getAllComments()).thenReturn(getCommentsList());
        when(getItemService().findAll()).thenReturn(getItemsList());

        assertEquals(1, getOperations().userComments.apply(getCommentsList(),5).size());
    }

    @Test
    public void testUserItems() {
        when(getItemService().findAll()).thenReturn(getItemsList());

        assertEquals(1, getOperations().userItems.apply(getItemsList(),5).size());
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


	public CommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public List<Comment> getCommentsList(){
		List<Comment> comments = new ArrayList<>();
		for(int i = 0; i<10; i++){
			Item item = new Item("Car"+i, "Car Description"+i, 0.5);
			User user = new User(i, "firstName"+i, "lastName"+i, "email"+i+"@gmail.com","123"+i, i%2==0);
			comments.add(new Comment( user,item,"good product"+i));
		}
		return comments;
	}

	public List<Item> getItemsList(){
		List<Item> items = new ArrayList<>();
		for(int i = 0; i<10; i++){
            User user = new User(i, "firstName"+i, "lastName"+i, "email"+i+"@gmail.com","123"+i, i%2==0);
            Item item = new Item("Car"+i, "Car Description"+i, 0.5, ItemCategoryEnum.CAR,user);
			items.add(item);
		}
		return items;
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
