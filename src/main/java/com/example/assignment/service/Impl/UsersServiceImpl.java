package com.example.assignment.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.assignment.model.Users;
import com.example.assignment.repository.UsersRepository;
import com.example.assignment.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService , UserDetailsService{

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void saveUser(Users users) {
		
		String password = passwordEncoder.encode(users.getPassword());
		
		users.setPassword(password);
		users.setRole("ROLE_"+users.getRole());
		usersRepository.save(users);
		
	}
	
	@Override
	public boolean adminExists(String email) {
		
		return usersRepository.findOneByEmailIgnoreCase(email).isPresent();
	}
	
	//saving Seed Data
	@Override
	public void run(String... args) throws Exception {
		
		if (!adminExists("Gyandeep1030@gmail.com")) {
			
			Users users = new Users();
			
			users.setName("Gyandeep");
			users.setEmail("Gyandeep1030@gmail.com");
			users.setPassword("123");
			users.setRole("ADMIN");
			
			saveUser(users);
		}
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Users> optionalUser = usersRepository.findOneByEmailIgnoreCase(username);
		
		if (!optionalUser.isPresent()) {
			throw new UsernameNotFoundException("User not found");
		}
		
		Users user = optionalUser.get();
		
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
		
		return new User(user.getEmail(), user.getPassword(), grantedAuthorities);
	}

	@Override
	public List<Users> getAllUsers() {
		
		return usersRepository.findAll();
	}

}
