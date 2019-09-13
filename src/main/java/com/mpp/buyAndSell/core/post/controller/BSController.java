package com.mpp.buyAndSell.core.post.controller;
import com.mpp.buyAndSell.core.post.repository.BSRepository;
import com.mpp.buyAndSell.core.post.repository.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
//import java.util.Optional;

@RestController
public class BSController {
    @Autowired
    private BSRepository bsRepository;
    @GetMapping("/posts")
    public List<Post> index(){
        return bsRepository.findAll();
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> show(@PathVariable String id) throws Exception {
        Long postId=Long.parseLong(id);
        Post post=bsRepository.findById(postId).orElseThrow(()->new Exception("Post"+postId+"not found"));
        return ResponseEntity.ok().body(post);  // .getOne vs findOne vs findById .... put problem ahead.
    }
    @PostMapping("/posts/search") //a search functionality by item name, the whole or part of item name
    public List<Post> search(@RequestBody String body){ //Map<String, String> body
        //String searchTerm=body.get("text");
        //return bsRepository.findByItemNameContainingOrItemDescriptionContaining(searchTerm,searchTerm);
        return bsRepository.findByItemNameContaining(body);
    }
//    public List<Post> search(@RequestBody Map<String, String> body){ //Map<String, String> body
//        String searchTerm=body.get("text");
//        return bsRepository.findByItemNameContainingOrItemDescriptionContaining(searchTerm,searchTerm);
//        //return bsRepository.findByItemNameContaining(body);
//    }

    @PostMapping("/posts")
    public Post create(@RequestBody Map<String,String> body){
        String itemName=body.get("itemName");
        String itemDescription=body.get("itemDescription");
        double price=Double.parseDouble(body.get("price"));
        return bsRepository.save(new Post(itemName,itemDescription,price));
    }

    @PutMapping("/posts/{id}")
    public Post update(@PathVariable String id, @RequestBody Map<String,String> body){
        Long postId=Long.parseLong(id);
        Post post= bsRepository.getOne(postId);
        post.setItemName(body.get("itemName"));
        post.setItemDescription(body.get("itemDescription"));
        post.setPrice(Double.parseDouble(body.get("price")));
        return bsRepository.save(post);
    }
    @DeleteMapping("/posts/{id}")
    public boolean delete(@PathVariable String id){
        Long postId=Long.parseLong(id);
        bsRepository.deleteById(postId);
        return true;
    }
}
