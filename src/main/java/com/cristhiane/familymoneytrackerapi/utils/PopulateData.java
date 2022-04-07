package com.cristhiane.familymoneytrackerapi.utils;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cristhiane.familymoneytrackerapi.models.ERole;
import com.cristhiane.familymoneytrackerapi.models.Role;
import com.cristhiane.familymoneytrackerapi.models.User;
import com.cristhiane.familymoneytrackerapi.repository.RoleRepository;
import com.cristhiane.familymoneytrackerapi.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class PopulateData {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@PostConstruct
	public void insertData() {
		Role role_moderator = new Role(ERole.ROLE_MODERATOR);
		Role role_user = new Role(ERole.ROLE_USER);
		Role role_admin = new Role(ERole.ROLE_ADMIN);

		User user_admin = new User("admin", "admin@teste.com", encoder.encode("12345678"));
		user_admin.getRoles().add(role_admin);
		
		User user_mod = new User("mod", "mod@teste.com", encoder.encode("12345678"));
		user_mod.getRoles().add(role_user);
		user_mod.getRoles().add(role_moderator);
		
		User user_normal = new User("normal_user", "normal_user@teste.com", encoder.encode("12345678"));
		user_normal.getRoles().add(role_user);
		
		roleRepository.saveAll(Arrays.asList(role_moderator, role_user, role_admin));
		userRepository.saveAll(Arrays.asList(user_admin, user_mod, user_normal));
		
	}
	
}
