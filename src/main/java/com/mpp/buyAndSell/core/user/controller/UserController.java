package com.mpp.buyAndSell.core.user.controller;

import com.mpp.buyAndSell.core.user.service.UserService;
import com.mpp.buyAndSell.core.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return getUserService().getUser(id);
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        return getUserService().addUser(user);
    }

    @PostMapping("/update")
    public User updateUser(@RequestBody User user){
        return getUserService().updateUser(user);
    }

    @GetMapping("/")
    public List<User> getAllUsers(){
        return getUserService().getAllUsers();
    }

    @PostMapping("/block")
    public User blockUser(@RequestBody Long id){
        return getUserService().blockUser(id);
    }

    @PostMapping("deactivate")
    public User deactivateUser(@RequestBody Long id){
        return getUserService().deactivateUser(id);
    }

    //---------------------------------------setters and getters----------------------------
    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
