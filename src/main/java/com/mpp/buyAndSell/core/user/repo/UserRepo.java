package com.mpp.buyAndSell.core.user.repo;

import com.mpp.buyAndSell.core.user.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    @Query("select month(u.createdDate), count(u.id) from User as u where month(CURRENT_DATE)-month(u.createdDate) < 6 group by month(u.createdDate)")
    public List<?> getUserDateChart();
	User findByEmail(String email);

    //User findByEmailAndPassword(String email, String password);

	User findByToken(String token);
}
