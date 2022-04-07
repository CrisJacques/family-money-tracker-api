package com.cristhiane.familymoneytrackerapi.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.cristhiane.familymoneytrackerapi.models.ERole;
import com.cristhiane.familymoneytrackerapi.models.Role;
import com.cristhiane.familymoneytrackerapi.models.User;
import com.cristhiane.familymoneytrackerapi.repository.UserRepository;

public class PopulateData {
	@Autowired
	UserRepository userRepository;
	
	@PostConstruct
	public void insertData() {
		Role role_moderator = new Role(ERole.ROLE_MODERATOR);
		Role role_user = new Role(ERole.ROLE_USER);
		Role role_admin = new Role(ERole.ROLE_ADMIN);
		
		User user_admin = new User("admin", "admin@teste.com", "12345678");
		user_admin.getRoles().add(role_admin);
		userRepository.save(user_admin);
		
		User user_mod = new User("mod", "mod@teste.com", "12345678");
		user_mod.getRoles().add(role_user);
		user_mod.getRoles().add(role_moderator);
		userRepository.save(user_mod);
		
		User user_normal = new User("normal_user", "normal_user@teste.com", "12345678");
		user_admin.getRoles().add(role_user);
		userRepository.save(user_normal);
		
	}
	
}
