package com.mpp.buyAndSell.core.user.role;

import com.mpp.buyAndSell.core.item.entity.Item;
import com.mpp.buyAndSell.core.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class Role {

    @Autowired
    private ItemService itemService;

    public Item createPost(Item item){
        return getItemService().addPost(item);
    }

    public boolean deletePost(Long id){
        return getItemService().deletePost(id);
    }

    public Item updatePost(Item item){
        return getItemService().updatePost(item);
    }

//    public Comment createComment(Comment comment){
//        to be implemented
//    }

//    public void showNotification(){
//        to be implemented
//    }

    //--------------------------------------setters and getters-------------------------

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }
}
