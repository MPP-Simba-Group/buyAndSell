package com.mpp.buyAndSell.core.user.role;

import com.mpp.buyAndSell.core.item.entity.Item;
import com.mpp.buyAndSell.core.user.entity.User;
import com.mpp.buyAndSell.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

public class Admin extends Role {

    @Autowired
    private UserService userService;

    public User blockReportedUser(User user){
        return getUserService().blockUser(user.getId());
    }

    public User getBestSeller(){
        return null;
    }

    public boolean deleteOutdatedPosts(){
        List<Item> items = getItemService().loadAllItems();
        for (Item item: items) {
            if (item.getExpiryDate().before(new Timestamp(System.currentTimeMillis()))){
                item.setActive(false);
                getItemService().updatePost(item);
            }
        }
        return true;
    }

    //------------------------setters and getters---------------------

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
