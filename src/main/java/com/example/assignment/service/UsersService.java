package com.example.assignment.service;

import java.util.List;

import org.springframework.boot.CommandLineRunner;

import com.example.assignment.model.Users;

public interface UsersService extends CommandLineRunner{
	
	public void saveUser(Users users);
	
	boolean adminExists(String email);
	
	public List<Users> getAllUsers();

}
