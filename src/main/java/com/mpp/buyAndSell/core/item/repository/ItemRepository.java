package com.mpp.buyAndSell.core.item.repository;

import com.mpp.buyAndSell.core.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
//   List<Item> findByItemNameContainingOrItemDescriptionContaining(String text, String text2);
   List<Item> findByItemNameContaining(String text);
}
