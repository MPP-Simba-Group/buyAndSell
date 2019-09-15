package com.mpp.buyAndSell.core.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mpp.buyAndSell.core.user.entity.User;
import com.mpp.buyAndSell.core.user.repo.UserRepo;
import com.mpp.buyAndSell.core.user.service.UserService;
import com.mpp.buyAndSell.jwt2.JwtTokenUtil;
import com.mpp.buyAndSell.jwt2Service.JwtUserDetailsServiceImpl;

@RestController
public class loginTestJwt2 {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private JwtUserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String pass) {

		System.out.println("====== login =======");

		try {
			//User user = userRepo.findByEmailAndPassword(email);
			User user = userRepo.findByEmail(email);
			if (user != null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(email);

				String token = jwtTokenUtil.generateToken(userDetails);
				user.setToken(token);
				
				userService.updateUser(user);
				return token;
			} else {
				return "user is not exist";
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "no token ";
		}
	}

	@RequestMapping(value = "/testToken", method = RequestMethod.POST)
	public String test(@RequestParam("token") String token) {

		
		System.out.println("====== login =======");
		System.out.println("token: "+token);
		//token = "eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2Mjg5ODk4MDksImlhdCI6MTU2ODUwOTgwOX0.osy9TRYwv6jUA6YkHSgSX1i7fvaD2LDkyDqsDbOvOmeMaL2kXFwWCt85HSV3jT_tPsqsP8EfLCxjWhUyv4-MVw";
		try {
			User user = userService.findUserByToken(token);
			String email = user.getEmail();
			System.out.println("email : "+email);
			return email;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "no token ";
		}
	}
	
	@RequestMapping(value = "/testToken2", method = RequestMethod.POST)
	public String test2(@RequestBody String token) {

		System.out.println("====== login =======");
		System.out.println("token: "+token);
		try {
			String email = jwtTokenUtil.getEmailFromToken(token);
			System.out.println("email : "+email);
			return email;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "no token ";
		}
	}
}
