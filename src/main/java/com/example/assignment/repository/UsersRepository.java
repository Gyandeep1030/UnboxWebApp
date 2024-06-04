package com.example.assignment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.assignment.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

	@Query("select a from Users a where a.email = :email")
	Optional<Users> findOneByEmailIgnoreCase(String email);
	
}
