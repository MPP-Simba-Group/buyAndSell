package com.mpp.buyAndSell.jwt2Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import com.mpp.buyAndSell.core.authanicateUser.entity.DAOUser;
//import com.mpp.buyAndSell.core.authanicateUser.repo.AuthanticateUserRepo;
import com.mpp.buyAndSell.core.user.entity.User;
import com.mpp.buyAndSell.core.user.repo.UserRepo;
import com.mpp.buyAndSell.core.user.service.UserService;
import com.mpp.buyAndSell.jwt2.JwtUserFactory;


/**
 * Created by stephan on 20.03.16.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	

	@Autowired
	private UserRepo userDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
		} else {
			return /*JwtUserFactory.create(user)*/null;
		}
	}

}
