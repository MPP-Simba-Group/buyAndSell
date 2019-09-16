package com.mpp.buyAndSell.core.statistics;

import com.mpp.buyAndSell.core.item.service.ItemService;
import com.mpp.buyAndSell.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/stat/")
@CrossOrigin
public class StatisticsController {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @GetMapping("userChart")
    public List<?> getUserDateChart(){
        return getUserService().getUserDateChart();
    }

    @GetMapping("cateChart")
    public List<?> getUserCategoryChart(){
        return getItemService().getItemCategoryChart();
    }

    @GetMapping("itemChart")
    public List<?> getItemDateChart(){
        return getItemService().getItemDateChart();
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }
}
