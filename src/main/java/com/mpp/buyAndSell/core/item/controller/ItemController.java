package com.mpp.buyAndSell.core.item.controller;
import com.mpp.buyAndSell.core.comment.entity.Comment;
import com.mpp.buyAndSell.core.item.entity.IowaCitiesEnum;
import com.mpp.buyAndSell.core.item.entity.Item;
import com.mpp.buyAndSell.core.item.entity.ItemCategoryEnum;
import com.mpp.buyAndSell.core.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import java.util.Optional;

@RestController
@RequestMapping("api/item/")
@CrossOrigin
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public List<Item> index(){
        return getItemService().findAll();
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> show(@PathVariable Long id) throws Exception {
        return getItemService().getPost(id);  // .getOne vs findOne vs findById .... put problem ahead.
    }

    @PostMapping("/items/search") //a search functionality by item name, the whole or part of item name
    public List<Item> search(@RequestBody String body){ //Map<String, String> body
        //String searchTerm=body.get("text");
        //return bsRepository.findByItemNameContainingOrItemDescriptionContaining(searchTerm,searchTerm);
        return getItemService().search(body);
    }
//    public List<Post> search(@RequestBody Map<String, String> body){ //Map<String, String> body
//        String searchTerm=body.get("text");
//        return bsRepository.findByItemNameContainingOrItemDescriptionContaining(searchTerm,searchTerm);
//        //return bsRepository.findByItemNameContaining(body);
//    }

    @PostMapping("/add")
    public Item addPost(@RequestBody Item item){
        return getItemService().addPost(item);
    }

    @PostMapping("/update")
    public Item update(@RequestBody Item item){
        return getItemService().updatePost(item);
    }

    @PostMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id){
        return getItemService().deletePost(id);
    }

    @PostMapping("comments")
    public List<Comment> getItemComments(@RequestBody Long itemId){
        return getItemService().getItemComments(itemId);
    }

    @GetMapping("categories")
    public List<ItemCategoryEnum> getAvailableItemCategories(){
        return getItemService().getAvailableItemCategories();
    }

    @GetMapping("cities")
    public List<IowaCitiesEnum> getIowaCities(){
        return getItemService().getIowaCities();
    }

    //----------------------------setters and getters-----------------------

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }
}
