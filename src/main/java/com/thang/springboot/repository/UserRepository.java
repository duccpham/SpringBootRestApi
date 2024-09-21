package com.thang.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.thang.springboot.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query(value = "select * from User where name = ?", nativeQuery = true)
	Optional<User> findByName (String name);
	
}
