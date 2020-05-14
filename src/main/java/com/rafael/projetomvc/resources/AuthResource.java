package com.rafael.projetomvc.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.projetomvc.security.JWTUtil;
import com.rafael.projetomvc.security.UserSS;
import com.rafael.projetomvc.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@RequestMapping(value = "/refresh_token" , method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse reponse){
		UserSS userSS = UserService.authenticated();
		String token = jwtUtil.generateToken(userSS.getUsername());
		reponse.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}

}
