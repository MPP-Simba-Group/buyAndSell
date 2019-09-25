package com.mpp.buyAndSell;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.*;

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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.mpp.buyAndSell.core.FunctionalProgramming.Operations;
import com.mpp.buyAndSell.core.comment.entity.Comment;
import com.mpp.buyAndSell.core.comment.repository.CommentRepository;
import com.mpp.buyAndSell.core.comment.service.CommentService;
import com.mpp.buyAndSell.core.item.entity.IowaCitiesEnum;
import com.mpp.buyAndSell.core.item.entity.Item;
import com.mpp.buyAndSell.core.item.entity.ItemCategoryEnum;
import com.mpp.buyAndSell.core.item.repository.ItemRepository;
import com.mpp.buyAndSell.core.item.service.ItemService;
import com.mpp.buyAndSell.core.user.entity.User;
import com.mpp.buyAndSell.core.user.repo.UserRepo;
import com.mpp.buyAndSell.core.user.service.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyAndSellApplicationTests {
	@Mock
	CommentService commentService;
	@Mock
	ItemService itemService;
	@Mock
	ItemRepository itemRepo;

    private User user;

	@Mock
	UserService userService;
	@Mock
	private CommentRepository commentRepository;

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
	public void getMostCityHasProductsTest() {
		//when(getUserService().getAllUsers()).thenReturn(getUsersList());
		when(getItemService().findAll()).thenReturn(getItemsList1());
		assertEquals(IowaCitiesEnum.Altoona, getOperations().getMostCityHasProducts.apply(getItemsList1()));
	}
	@Test
	public void getMostedActiveSellerTest() {
		//when(getUserService().getAllUsers()).thenReturn(getUsersList());
		when(getItemService().findAll()).thenReturn(getItemsList1());
		assertEquals(2, getOperations().getMostedActiveSeller.apply(getItemsList1()).getId());
	}
	@Test
	public void getMostedActiveCommenterTest() {
		//when(getUserService().getAllUsers()).thenReturn(getUsersList());
		when(getCommentService().getAllComments()).thenReturn(getCommentsList1());
		assertEquals(2, getOperations().getMostedActiveCommenter.apply(getCommentsList1()).getId());
	}

	@Test
	public void testGetProductsAfterDate() {
		assertEquals(new ArrayList<Item>(), getOperations().getProductsAfterDate.apply(getItemsList2(), new Timestamp(System.currentTimeMillis())));
		assertEquals(getItemsList().size(), getOperations().getProductsAfterDate.apply(getItemsList2(), new Timestamp(System.currentTimeMillis()-2*24*60*60*1000)).size());
	}
	/*@Test
	public void getProductsWithKdaysTest() {
		when(getItemService().findAll()).thenReturn(getItemsList());
		assertEquals(13, getOperations().getProductsWithKdays.apply(getItemsList(),(long)1,LocalDate.now()).size());

	}*/



	public CommentRepository getCommentRepository() {
		return commentRepository;
	}
	public void setCommentRepository(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
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

	@Test
	public void testTopActiveItems(){
		assertEquals(5, getOperations().topActiveItems.apply(getItemsList2()).size());
        assertEquals(new ArrayList<Item>(), getOperations().topActiveItems.apply(new ArrayList<Item>()));
    }

    @Test
    public void testTotalItemPriceInYear(){
	    assertEquals(Optional.of(145.0), getOperations().totalItemPriceInYear.apply(getItemsList2()));
	    assertEquals(Optional.empty(), getOperations().totalItemPriceInYear.apply(new ArrayList<>()));
    }

    @Test
    public void testTotalItemPriceInQuarter(){
        assertEquals(Optional.of(145.0), getOperations().totalItemPriceInQuarter.apply(getItemsList2()));
        assertEquals(Optional.empty(), getOperations().totalItemPriceInQuarter.apply(new ArrayList<>()));
    }

    @Test
    public void testTop5Commenters(){
	    assertEquals(5, getOperations().top5Commentors.apply(getComments()).size());
    }

    @Test
    public void testTop5Sellers(){
        assertEquals(5, getOperations().top5Sellers.apply(getItemsList2()).size());
    }

    @Test
    public void testMostActiveMonth(){
	    assertEquals(/*september*/8, getOperations().mostActiveMonth.apply(getItemsList2()).intValue());
//	    assertEquals(doThrow(new NoSuchElementException("No value present")), getOperations().mostActiveMonth.apply(new ArrayList<>()).intValue());
    }

    @Test
    public void testItemsExceedsPrice() {
	    assertEquals(4, getOperations().itemsExceedsPrice.apply(getItemsList2(), 15).size());
	    assertArrayEquals(new Object[0],getOperations().itemsExceedsPrice.apply(getItemsList2(), 100).toArray());
    }

    @Test
    public void testMostLikedItemInMonth() {
	    assertNotEquals(new Item(), getOperations().mostLikedItemInMonth.apply(getItemsList2(), 8));
	    assertEquals(29, getOperations().mostLikedItemInMonth.apply(getItemsList2(),8).get().getLikes());
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

	public ItemRepository getItemRepo() {
		return itemRepo;
	}
	public void setItemRepo(ItemRepository itemRepo) {
		this.itemRepo = itemRepo;
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
	public List<Item> getItemsList1() {
		List<Item> items = new ArrayList<>();
		for (int i = 0; i < 13; i++) {
			User user = new User(i, "firstName" + i, "lastName" + i, "email" + i + "@gmail.com", "123" + i, i % 2 == 0);
			IowaCitiesEnum city;
			if (i < 5) {
				city = IowaCitiesEnum.Iowa_City;
			} else {
				city = IowaCitiesEnum.Altoona;
			}
			if (user.getId() == 2) {
				Item item = new Item((long) 13, "Name" + i, "decs" + i, 30.0, user, city, new Timestamp(System.currentTimeMillis() - 24 * 60 * 60 * 1000));
				items.add(item);
			}
			//User user = new User(i, "firstName"+i, "lastName"+i, "email"+i+"@gmail.com","123"+i, i%2==0);
			//Item item=new Item(i, "firstName"+i, "itemDescription"+i, 0, user, 	, createdTime)
			Item item = new Item((long) i, "Name" + i, "decs" + i, 30.0, user, city, new Timestamp(System.currentTimeMillis() - 24 * 60 * 60 * 1000));
//			item.setCreatedTime(new Timestamp(System.currentTimeMillis()));
			items.add(item);
		}
		/*public List<Comment> getCommentsList(){
			List<Comment> comments = new ArrayList<>();
			for(int i = 0; i<10; i++){
				Item item = new Item("Car"+i, "Car Description"+i, 0.5);
				User user = new User(i, "firstName"+i, "lastName"+i, "email"+i+"@gmail.com","123"+i, i%2==0);
				comments.add(new Comment( user,item,"good product"+i));
			}
			return comments;}*/


		return items;
	}

	public List<Item> getItemsList2(){
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

	public List<Comment> getCommentsList1(){
		List<Comment> comments = new ArrayList<>();
		for(int i = 0; i<10; i++){
			Item item = new Item("Car"+i, "Car Description"+i, 0.5);
			User user = new User(i, "firstName"+i, "lastName"+i, "email"+i+"@gmail.com","123"+i, i%2==0);
			if(user.getId()==2) {
				comments.add(new Comment( user,item,"good product"+i));
            }
			comments.add(new Comment( user,item,"good product"+i));
		}
		return comments;}
	}
