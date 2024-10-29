package com.root.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.root.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>, PagingAndSortingRepository<Admin, Long> {

}
