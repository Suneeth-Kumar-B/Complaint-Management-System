package com.root.dao;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import com.root.models.User;
import com.root.repositories.UserRepository;

@Component
public class UserDAOImpl implements UserDAO {

	UserRepository userRepository;
	
	UserDAOImpl(UserRepository userRepository){
		this.userRepository=userRepository;
	}
	
	@Override
	public void newUser(User user) {
	    userRepository.save(user);
	}

	@Override
	public User validateUser(User user) {
		ExampleMatcher match = ExampleMatcher.matching()
				.withIgnorePaths("id","name","email","phone")
				.withMatcher("username", ExampleMatcher.GenericPropertyMatchers.exact())
				.withMatcher("password", ExampleMatcher.GenericPropertyMatchers.exact());
		
		Example<User> example = Example.of(user, match);
		Optional<User> active = userRepository.findOne(example);
		if(active.isEmpty())
			return null;		
		return active.get();
	}
	
}