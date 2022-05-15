package com.cristhiane.familymoneytrackerapi.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cristhiane.familymoneytrackerapi.domain.User;
import com.cristhiane.familymoneytrackerapi.repository.UserRepository;

/**
 * Service responsável por carregar informações do usuário
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	/**
	 * Busca o usuário que possui o e-mail passado por parâmetro
	 * 
	 * @return Dados do usuário cujo e-mail foi passado por parâmetro
	 * @throws UsernameNotFoundException Caso não exista usuário com o e-mail
	 *                                   informado
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
		return UserDetailsImpl.build(user);
	}
}
