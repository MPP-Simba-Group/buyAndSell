package com.mpp.buyAndSell;

import com.mpp.buyAndSell.core.FunctionalProgramming.Operations;
import com.mpp.buyAndSell.core.comment.entity.Comment;
import com.mpp.buyAndSell.core.comment.repository.CommentRepository;
import com.mpp.buyAndSell.core.comment.service.CommentService;
import com.mpp.buyAndSell.core.item.entity.IowaCitiesEnum;
import com.mpp.buyAndSell.core.item.entity.Item;
import com.mpp.buyAndSell.core.item.repository.ItemRepository;
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

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyAndSellApplicationTests {
	@Mock
	CommentService commentService;
	@Mock
	ItemService itemService;
	@Mock
	ItemRepository itemRepo;

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
		when(getItemService().findAll()).thenReturn(getItemsList());
		assertEquals(IowaCitiesEnum.Altoona, getOperations().getMostCityHasProducts.apply(getItemsList()));
	}
	@Test
	public void getMostedActiveSellerTest() {
		//when(getUserService().getAllUsers()).thenReturn(getUsersList());
		when(getItemService().findAll()).thenReturn(getItemsList());
		assertEquals(2, getOperations().getMostedActiveSeller.apply(getItemsList()).getId());
	}
	@Test
	public void getMostedActiveCommenterTest() {
		//when(getUserService().getAllUsers()).thenReturn(getUsersList());
		when(getCommentService().getAllComments()).thenReturn(getCommentsList());
		assertEquals(2, getOperations().getMostedActiveCommenter.apply(getCommentsList()).getId());
	}
	
	@Test
	public void testGetProductsAfterDate() {
		assertEquals(new ArrayList<Item>(), getOperations().getProductsAfterDate.apply(getItemsList(), new Timestamp(System.currentTimeMillis())));
		assertEquals(getItemsList().size(), getOperations().getProductsAfterDate.apply(getItemsList(), new Timestamp(System.currentTimeMillis()-2*24*60*60*1000)).size());
	}
	/*@Test
	public void getProductsWithKdaysTest() {
		when(getItemService().findAll()).thenReturn(getItemsList());
		assertEquals(13, getOperations().getProductsWithKdays.apply(getItemsList(),(long)1,LocalDate.now()).size());

	}*/

	
	public CommentService getCommentService() {
		return commentService;
	}
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	public CommentRepository getCommentRepository() {
		return commentRepository;
	}
	public void setCommentRepository(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
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

	public ItemService getItemService() {
		return itemService;
	}
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
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
		for(int i = 0; i<13; i++){
			User user = new User(i, "firstName"+i, "lastName"+i, "email"+i+"@gmail.com","123"+i, i%2==0);
			IowaCitiesEnum city;
            if(i<5) {city=IowaCitiesEnum.Iowa_City;}else {
            	city=IowaCitiesEnum.Altoona;
            }
            if(user.getId()==2) {
            	Item item=new Item((long)13, "Name"+i,"decs"+i, 30.0, user, city, new Timestamp(System.currentTimeMillis()-24*60*60*1000));
    			items.add(item);
            }
			//User user = new User(i, "firstName"+i, "lastName"+i, "email"+i+"@gmail.com","123"+i, i%2==0);
			//Item item=new Item(i, "firstName"+i, "itemDescription"+i, 0, user, 	, createdTime)
			Item item=new Item((long)i, "Name"+i,"decs"+i, 30.0, user, city, new Timestamp(System.currentTimeMillis()-24*60*60*1000));
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
	public List<Comment> getCommentsList(){
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
