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

    public User getUser(Long id) {
        return getUserRepo().findById(id).get();
    }

    public User updateUser(User user) {
        return getUserRepo().save(user);
    }

    public List<User> getAllUsers() {
        return (List<User>) getUserRepo().findAll();
    }

    public User blockUser(Long id) {
        Optional<User> optionalUser = getUserRepo().findById(id);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setBlocked(true);
            getUserRepo().save(user);
            return user;
        }
        return null;
    }

    public User deactivateUser(Long id) {
        Optional<User> optionalUser = getUserRepo().findById(id);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setActive(false);
            getUserRepo().save(user);
            return user;
        }
        return null;
    }

    public List<?> getUserDateChart(){
        return getUserRepo().getUserDateChart();
    }

    //------------------------------------setters and getters--------------------------
    public UserRepo getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

}
