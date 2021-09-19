package com.carscan.api.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carscan.api.admin.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAll();
	Optional<User> findById(long id);
	void deleteById(String userId);
}