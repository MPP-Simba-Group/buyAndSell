package com.mpp.buyAndSell.core.post.service;

import com.mpp.buyAndSell.core.post.entity.Post;
import com.mpp.buyAndSell.core.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll() {
        return getPostRepository().findAll();
    }

    public ResponseEntity getPost(Long id) throws Exception {
        Post post= getPostRepository().findById(id).orElseThrow(()->new Exception("Post: "+id+" not found!"));
        return ResponseEntity.ok().body(post);
    }

    public List<Post> search(@RequestBody String body){
        return getPostRepository().findByItemNameContaining(body);
    }

    public Post addPost(Post post) {
        return getPostRepository().save(post);
    }

    public Post updatePost(Post post) {
        return getPostRepository().save(post);
    }

    public boolean deletePost(Long id) {
        getPostRepository().deleteById(id);
        return true;
    }

    //------------------------------setters and getters--------------------

    public PostRepository getPostRepository() {
        return postRepository;
    }

    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

}
