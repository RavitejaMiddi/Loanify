package com.loanify.controllers;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loanify.entities.User;
import com.loanify.exceptions.InvalidUserException;
import com.loanify.jwt.JwtTokenUtil;
import com.loanify.jwt.LoginData;
import com.loanify.jwt.TokenEntity;
import com.loanify.repositories.IUserRepository;



@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("login")
public class AuthenticationController {
	
	Logger logger = Logger.getLogger(AuthenticationController.class.getName());
	
	

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	IUserRepository userRepository;
	
	User user;
	
	public AuthenticationController() {
		logger.log(Level.INFO,"-----> Inside Authentication Service Controller Working!");
	}
	//request for authorizing user by generating token from JwtTokenUtil class
	@PostMapping
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginData data) {

		Optional<User> userData = userRepository.findByName(data.getUserName());

		if (userData.isPresent()) {
			user = userData.get();
		} else {
			throw new InvalidUserException("User not found with username: " + data.getUserName());
		}

		if (!(user.getPassword().equals(data.getPassword())))
			throw new InvalidUserException("Invalid Password");

		String token = jwtTokenUtil.generateToken(user);

		return ResponseEntity.ok(new TokenEntity(token));
		
		//return new ResponseEntity<String>("token",HttpStatus.OK);

	}


}
