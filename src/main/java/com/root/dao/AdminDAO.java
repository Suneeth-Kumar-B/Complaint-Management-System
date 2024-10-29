package com.root.dao;

import com.root.models.Admin;

public interface AdminDAO {
	void newAdmin(Admin admin);
	Admin validateAdmin(Admin admin);
}
