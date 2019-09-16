package com.mpp.buyAndSell.core.item.repository;

import com.mpp.buyAndSell.core.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
//   List<Item> findByItemNameContainingOrItemDescriptionContaining(String text, String text2);
   List<Item> findByItemNameContaining(String text);

   @Query("select i.category, count(i.id) from Item as i group by i.category")
   List<?> getItemCategoryChart();

   @Query("select month(i.createdTime), count(i.id) from Item as i where month(CURRENT_DATE)-month(i.createdTime) < 6 group by month(i.createdTime)")
   List<?> getItemDateChart();
}
