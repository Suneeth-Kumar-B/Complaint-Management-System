package com.root.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.root.models.User;

public interface UserRepository extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long>{

}
