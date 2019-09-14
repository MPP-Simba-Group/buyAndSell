package com.mpp.buyAndSell.core.comment.repository;

import com.mpp.buyAndSell.core.comment.entity.Comment;
import com.mpp.buyAndSell.core.item.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByItem(Item item);
}
