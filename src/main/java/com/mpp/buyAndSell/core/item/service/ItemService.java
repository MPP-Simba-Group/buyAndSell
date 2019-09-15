package com.mpp.buyAndSell.core.item.service;

import com.mpp.buyAndSell.core.comment.entity.Comment;
import com.mpp.buyAndSell.core.comment.service.CommentService;
import com.mpp.buyAndSell.core.item.entity.Item;
import com.mpp.buyAndSell.core.item.entity.ItemCategoryEnum;
import com.mpp.buyAndSell.core.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CommentService commentService;

    public List<Item> findAll() {
        return getItemRepository().findAll();
    }

    public ResponseEntity getPost(Long id) throws Exception {
        Item item = getItemRepository().findById(id).orElseThrow(()->new Exception("Post: "+id+" not found!"));
        return ResponseEntity.ok().body(item);
    }

    public List<Item> search(@RequestBody String body){
        return getItemRepository().findByItemNameContaining(body);
    }

    public Item addPost(Item item) {
        return getItemRepository().save(item);
    }

    public Item updatePost(Item item) {
        return getItemRepository().save(item);
    }

    public boolean deletePost(Long id) {
        getItemRepository().deleteById(id);
        return true;
    }

    public List<Item> loadAllItems() {
        return getItemRepository().findAll();
    }

    public List<Comment> getItemComments(Long itemId) {
        Item item = getItemRepository().findById(itemId).get();
        return getCommentService().getItemComments(item);
    }

    public List<ItemCategoryEnum> getAvailableItemCategories() {
        return Arrays.asList(ItemCategoryEnum.values());
    }

    //------------------------------setters and getters--------------------

    public ItemRepository getItemRepository() {
        return itemRepository;
    }

    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public CommentService getCommentService() {
        return commentService;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

}
