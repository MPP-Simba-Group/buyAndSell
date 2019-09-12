package com.mpp.buyAndSell.core.user.service;

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

    public User addUser(User user){
        return getUserRepo().save(user);
    }

    public Optional<User> getUser(Long id) {
        return getUserRepo().findById(id);
    }

    public User updateUser(User user) {
        return getUserRepo().save(user);
    }

    public List<User> getAllUsers() {
        return (List<User>) getUserRepo().findAll();
    }

    //------------------------------------setters and getters--------------------------
    public UserRepo getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

}
