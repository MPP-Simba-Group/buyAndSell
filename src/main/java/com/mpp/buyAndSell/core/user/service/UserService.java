package com.mpp.buyAndSell.core.user.service;

import com.mpp.buyAndSell.core.FunctionalProgramming.Operations;
import com.mpp.buyAndSell.core.comment.entity.Comment;
import com.mpp.buyAndSell.core.comment.service.CommentService;
import com.mpp.buyAndSell.core.item.entity.Item;
import com.mpp.buyAndSell.core.item.service.ItemService;
import com.mpp.buyAndSell.core.user.entity.User;
import com.mpp.buyAndSell.core.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private Operations operations;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ItemService itemService;

    public User addUser(User user){
        return getUserRepo().save(user);
    }

    public User getUser(Long id) {
        return getUserRepo().findById(id).get();
    }


    public User updateUser(User user) {
        return getUserRepo().save(user);
    }

    public List<User> getAllUsers() {
        return (List<User>) getUserRepo().findAll();
    }

    public User blockUser(Long id) {
        Optional<User> optionalUser = getUserRepo().findById(id);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setBlocked(true);
            getUserRepo().save(user);
            return user;
        }
        return null;
    }

    public User deactivateUser(Long id) {
        Optional<User> optionalUser = getUserRepo().findById(id);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setActive(false);
            getUserRepo().save(user);
            return user;
        }
        return null;
    }

    public User findUserByToken(String token) {
    	return getUserRepo().findByToken(token);
    }

    public List<?> getUserDateChart(){
        return getUserRepo().getUserDateChart();
    }

    public List<User> getBlockedUsers(){
        return getOperations().blockedUsers.apply(getAllUsers());
    }

    public List<Comment> getUserComments(User u){
        return getOperations().userComments.apply(getCommentService().getAllComments(),u);
    }

    public List<Item> getUserItems(User u){
        return getOperations().userItems.apply(getItemService().findAll(),u);
    }

    public List<User> getTop5Sellers(){
        return getOperations().top5Sellers.apply(getItemService().findAll());
    }

    public List<User> getTop5Buyers(){
        return getOperations().top5Commentors.apply(getCommentService().getAllComments());
    }

    //------------------------------------setters and getters--------------------------
    public UserRepo getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public Operations getOperations() {
        return operations;
    }

    public void setOperations(Operations operations) {
        this.operations = operations;
    }

    public CommentService getCommentService() {
        return commentService;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }
}
