package com.mpp.buyAndSell.core.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BSRepository extends JpaRepository<Post,Long> {
   //List<Post> findByItemNameContainingOrContentContaining(String text);
}
