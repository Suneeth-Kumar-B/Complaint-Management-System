package com.root.dao;

import com.root.models.User;

public interface UserDAO {
	void newUser(User user);
	User validateUser(User user);
}
