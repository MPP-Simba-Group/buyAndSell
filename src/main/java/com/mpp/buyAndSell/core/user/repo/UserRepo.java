package com.mpp.buyAndSell.core.user.repo;

import com.mpp.buyAndSell.core.user.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
	User findByEmail(String email);
	
    //User findByEmailAndPassword(String email, String password);
	
	User findByToken(String token);
}
