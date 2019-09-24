package com.mpp.buyAndSell;

import com.mpp.buyAndSell.core.FunctionalProgramming.Operations;
import com.mpp.buyAndSell.core.comment.entity.Comment;
import com.mpp.buyAndSell.core.item.entity.Item;
import com.mpp.buyAndSell.core.user.entity.User;
import com.mpp.buyAndSell.core.user.repo.UserRepo;
import com.mpp.buyAndSell.core.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyAndSellApplicationTests {

    private User user;

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

	@Test
	public void testTopActiveItems(){
		assertEquals(5, getOperations().topActiveItems.apply(getItemsList()).size());
        assertEquals(new ArrayList<Item>(), getOperations().topActiveItems.apply(new ArrayList<Item>()));
    }

    @Test
    public void testTotalItemPriceInYear(){
	    assertEquals(Optional.of(145.0), getOperations().totalItemPriceInYear.apply(getItemsList()));
	    assertEquals(Optional.empty(), getOperations().totalItemPriceInYear.apply(new ArrayList<>()));
    }

    @Test
    public void testTotalItemPriceInQuarter(){
        assertEquals(Optional.of(145.0), getOperations().totalItemPriceInQuarter.apply(getItemsList()));
        assertEquals(Optional.empty(), getOperations().totalItemPriceInQuarter.apply(new ArrayList<>()));
    }

    @Test
    public void testTop5Commenters(){
	    assertEquals(5, getOperations().top5Commentors.apply(getComments()).size());
    }

    @Test
    public void testTop5Sellers(){
        assertEquals(5, getOperations().top5Sellers.apply(getItemsList()).size());
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

	public List<Item> getItemsList(){
		List<Item> items = new ArrayList<>();
		for (int i = 0; i<10; i++){
		    Item item = new Item((long) i, "itemName"+i, "description"+i, 10+i, 20+i, getComments(), new Timestamp(System.currentTimeMillis()));
		    User user = new User((long)i, "username"+i);
		    item.setUser(user);
		    if(i == 5){
		        this.user = user;
            }
			items.add(item);
		}
		return items;
	}

	public List<Comment> getComments(){
		List<Comment> comments = new ArrayList<>();
		for (int i = 0; i<20; i++){
			comments.add(new Comment(new User((long)i, "name"+i)));
		}
		return comments;
	}
}
