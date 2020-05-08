package com.rafael.projetomvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rafael.projetomvc.dominio.Cliente;
import com.rafael.projetomvc.repository.ClienteRepository;
import com.rafael.projetomvc.security.UserSS;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private ClienteRepository clientRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cli = clientRepository.findByEmail(email);
		if(cli == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserSS(cli.getId(),cli.getEmail(),cli.getSenha(),cli.getPerfis());
		
		
	}

}
