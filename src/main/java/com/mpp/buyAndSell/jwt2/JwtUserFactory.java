package com.mpp.buyAndSell.jwt2;

import com.mpp.buyAndSell.core.user.entity.User;

public final class JwtUserFactory {

	private JwtUserFactory() {
	}

	public static User create(User user) {
		//return new User(user.getId(),user.getFirstName(), user.getLastName(), user.getEmail());
		return new User(user.getId(),user.getFirstName(), user.getLastName(), user.getEmail(),user.getPassword());
	}
}
