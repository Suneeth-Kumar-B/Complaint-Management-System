package com.root.services;

import com.root.models.User;

public interface UserService {
	void newUser(User user);
	User validateUser(String username, String password);
}
