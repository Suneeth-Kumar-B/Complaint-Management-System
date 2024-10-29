package com.root.dao;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import com.root.models.Admin;
import com.root.repositories.AdminRepository;

@Component
public class AdminDAOImpl implements AdminDAO {
	
	AdminRepository adminRepository;
	
	public AdminDAOImpl(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;		
	}

	@Override
	public void newAdmin(Admin admin) {
		adminRepository.save(admin);		
	}

	@Override
	public Admin validateAdmin(Admin admin){
		ExampleMatcher match = ExampleMatcher.matching()
				.withIgnorePaths("name","email","phone","emergency_codes")
				.withMatcher("aid", ExampleMatcher.GenericPropertyMatchers.exact())
				.withMatcher("password", ExampleMatcher.GenericPropertyMatchers.exact());
		
		Example<Admin> example = Example.of(admin, match);
		Optional<Admin> active = adminRepository.findOne(example);
		if(active.isEmpty())
			return null;		
		return active.get();
	}

}
