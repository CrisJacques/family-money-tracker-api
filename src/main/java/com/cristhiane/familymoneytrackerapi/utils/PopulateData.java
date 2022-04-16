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
		Role role_group_user = new Role(ERole.ROLE_GROUP_USER);
		Role role_group_admin = new Role(ERole.ROLE_GROUP_ADMIN);

		User group_admin = new User("group_admin", "group_admin@teste.com", encoder.encode("Gr0up@adm1n_T3st3"));
		group_admin.getRoles().add(role_group_admin);
		
		User group_user = new User("group_user", "group_user@teste.com", encoder.encode("Gr0upUs3r_T3st3"));
		group_user.getRoles().add(role_group_user);
		
		roleRepository.saveAll(Arrays.asList(role_group_user, role_group_admin));
		userRepository.saveAll(Arrays.asList(group_admin, group_user));
		
	}
	
}
