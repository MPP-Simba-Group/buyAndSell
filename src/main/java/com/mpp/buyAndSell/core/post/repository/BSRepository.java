package com.mpp.buyAndSell.core.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BSRepository extends JpaRepository<Post,Long> {
   List<Post> findByItemNameContainingOrItemDescriptionContaining(String text, String text2);
   List<Post> findByItemNameContaining(String text);
}
