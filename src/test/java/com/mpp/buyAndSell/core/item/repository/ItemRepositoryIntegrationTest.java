package com.mpp.buyAndSell.core.item.repository;

import com.mpp.buyAndSell.core.item.entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemRepositoryIntegrationTest {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private ItemRepository itemRepository;

	@Test
	public void findByItemNameContaining() {
		List<Item> post= new ArrayList<>();
		post.add(new Item("Google X","excellent smart phone", 577.0));
		entityManager.persist(post.get(0));
		entityManager.flush();
		List<Item> found= itemRepository.findByItemNameContaining("Google");
		assertThat(found.get(0).getItemName()).isEqualTo("Google X");
	}
}