package com.mpp.buyAndSell.core.item.controller;

import com.mpp.buyAndSell.core.item.entity.Item;
import com.mpp.buyAndSell.core.item.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

	@Autowired
	private MockMvc mvc;
	@MockBean
	private ItemService service;
//	@Before
//	public void setUp() throws Exception{
//		mockMvc= MockMvcBuilders.standaloneSetup(itemController).build();
//	}
	@Test
	public void index() throws Exception {
		List<Item> items=new ArrayList<>();
		Item item1=new Item("iPhone X","The smart phone from apple", 675);
		Item item2=new Item("iPhone XR", "Another smart phone from Apple", 543);
		Item item3= new Item("Nokia 8", "Smart phone from HMD Global", 340);
		items.add(item1);
		items.add(item2);
		items.add(item3);
		given(service.findAll()).willReturn(items);

		mvc.perform(get("/api/item/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)));
	}

	//@Test
	/*public void show() throws Exception {
		List<Item> items=new ArrayList<>();
		Item item1 = new Item(1l,"iPhone X","The smart phone from apple", 675);
		Item item2 = new Item(2l,"iPhone XR", "Another smart phone from Apple", 543);
		Item item3 = new Item(3l,"Nokia 8", "Smart phone from HMD Global", 340);
		items.add(item1);
		items.add(item2);
		items.add(item3);
		given(service.getPost(1L)).willReturn(ResponseEntity.ok().body(item2));
		mvc.perform(get("/api/item/items/{id}/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)));
	}*/

	/*@Test
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
	}*/
}