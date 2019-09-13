package com.mpp.buyAndSell.core.post.controller;
import com.mpp.buyAndSell.core.post.repository.PostRepository;
import com.mpp.buyAndSell.core.post.entity.Post;
import com.mpp.buyAndSell.core.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
//import java.util.Optional;

@RestController
@RequestMapping("api/post/")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public List<Post> index(){
        return getPostService().findAll();
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> show(@PathVariable Long id) throws Exception {
        return getPostService().getPost(id);  // .getOne vs findOne vs findById .... put problem ahead.
    }

    @PostMapping("/posts/search") //a search functionality by item name, the whole or part of item name
    public List<Post> search(@RequestBody String body){ //Map<String, String> body
        //String searchTerm=body.get("text");
        //return bsRepository.findByItemNameContainingOrItemDescriptionContaining(searchTerm,searchTerm);
        return getPostService().search(body);
    }
//    public List<Post> search(@RequestBody Map<String, String> body){ //Map<String, String> body
//        String searchTerm=body.get("text");
//        return bsRepository.findByItemNameContainingOrItemDescriptionContaining(searchTerm,searchTerm);
//        //return bsRepository.findByItemNameContaining(body);
//    }

    @PostMapping("/add")
    public Post addPost(@RequestBody Post post){
        return getPostService().addPost(post);
    }

    @PostMapping("/update")
    public Post update(@RequestBody Post post){
        return getPostService().updatePost(post);
    }
    @PostMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id){
        return getPostService().deletePost(id);
    }

    //----------------------------setters and getters-----------------------

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }
}
