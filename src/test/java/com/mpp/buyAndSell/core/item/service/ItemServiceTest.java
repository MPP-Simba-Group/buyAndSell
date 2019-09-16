package com.mpp.buyAndSell.core.item.service;

import com.mpp.buyAndSell.core.item.entity.Item;
import com.mpp.buyAndSell.core.item.repository.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {
	@InjectMocks
	ItemService itemService;
	@Mock
	ItemRepository itemRepository;
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void findAll() {
		List<Item> items=new ArrayList<>();
		Item item1=new Item("iPhone X","The smart phone from apple", 675);
		Item item2=new Item("iPhone XR", "Another smart phone from Apple", 543);
		Item item3= new Item("Nokia 8", "Smart phone from HMD Global", 340);
		items.add(item1);
		items.add(item2);
		items.add(item3);
		when(itemRepository.findAll()).thenReturn(items);
		List<Item> itemlst=itemRepository.findAll();
		assertEquals(3,itemlst.size());
		verify(itemRepository,times(1)).findAll();
	}


	@Test
	public void getPost() {
		List<Item> items=new ArrayList<>();
		Item item1=new Item("iPhone X","The smart phone from apple", 675);
		Item item2=new Item("iPhone XR", "Another smart phone from Apple", 543);
		Item item3= new Item("Nokia 8", "Smart phone from HMD Global", 340);
		items.add(item1);
		items.add(item2);
		items.add(item3);

		when(itemRepository.getOne(1L)).thenReturn(item2);

		Item secondItem=itemRepository.getOne(1L);
		assertEquals("iPhone XR",secondItem.getItemName());
		assertEquals("Another smart phone from Apple",secondItem.getItemDescription());
//		assertEquals(675,secondItem.getPrice(),)
		assertEquals(543,secondItem.getPrice(),0.001);

	}

	@Test
	public void search() {
		List<Item> items=new ArrayList<>();
		Item item1=new Item("iPhone X","The smart phone from apple", 675);
		Item item2=new Item("iPhone XR", "Another smart phone from Apple", 543);
		Item item3= new Item("Nokia 8", "Smart phone from HMD Global", 340);
		items.add(item2);
		List<Item> items2=new ArrayList<>(); items2.add(item1);items2.add(item2);
		List<Item> items3=new ArrayList<>(); items3.add(item3);
		when(itemRepository.findByItemNameContaining("XR")).thenReturn(items);
		when(itemRepository.findByItemNameContaining("iPhone")).thenReturn(items2);

		List<Item> search1=itemService.search("XR");
		assertEquals(1,search1.size());
		List<Item> search2=itemService.search("iPhone");
		assertEquals(2,search2.size());

	}

	@Test
	public void addPost() {
		List<Item> items=new ArrayList<>();
		Item item1=new Item("iPhone X","The smart phone from apple", 675);
		when(itemRepository.save(item1)).thenReturn(item1);
		//
		assertEquals("iPhone X",itemRepository.save(item1).getItemName());
		assertEquals("The smart phone from apple",itemRepository.save(item1).getItemDescription());
	}

//	@Test
//	public void updatePost() {
//	}
//
//	@Test
//	public void deletePost() {
//	}
//
//	@Test
//	public void loadAllItems() {
//	}
//
//	@Test
//	public void getItemComments() {
//	}
//
//	@Test
//	public void getAvailableItemCategories() {
//	}
//
//	@Test
//	public void getItemRepository() {
//	}
//
//	@Test
//	public void setItemRepository() {
//	}
//
//	@Test
//	public void getCommentService() {
//	}
//
//	@Test
//	public void setCommentService() {
//	}
}