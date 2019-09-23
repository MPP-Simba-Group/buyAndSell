package com.mpp.buyAndSell.core.comment.service;

import com.mpp.buyAndSell.core.comment.entity.Comment;
import com.mpp.buyAndSell.core.comment.repository.CommentRepository;
import com.mpp.buyAndSell.core.item.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment addComment(Comment comment) {
        return getCommentRepository().save(comment);
    }

    public List<Comment> getItemComments(Item item) {
        List<Comment> comments = getCommentRepository().findByItem(item);
        return comments;
    }

    public List<Comment> getAllComments(){
        return (List<Comment>) getCommentRepository().findAll();
    }

    //---------------------------------------setters and getters----------------------
    public CommentRepository getCommentRepository() {
        return commentRepository;
    }

    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

}
