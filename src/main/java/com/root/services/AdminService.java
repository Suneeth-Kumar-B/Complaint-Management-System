package com.root.services;

import com.root.models.Admin;

public interface AdminService {
	void newAdmin(Admin admin);
	Admin validateAdmin(long aid, String password);
}