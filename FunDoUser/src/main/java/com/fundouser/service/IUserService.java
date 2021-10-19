package com.fundouser.service;


import java.sql.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fundouser.Exception.FunDoNotesCutomException;
import com.fundouser.config.PasswordEncoderConfig;
import com.fundouser.dto.LoginDto;
import com.fundouser.dto.UpdatePasswordDto;
import com.fundouser.dto.UserDto;
import com.fundouser.entity.User;
import com.fundouser.repository.UserRepo;
import com.fundouser.utils.EmailSender;
import com.fundouser.utils.TokenService;


@Service
public class IUserService implements UserService {
	
	@Autowired
	UserRepo userRepository;
	
	@Autowired
	PasswordEncoderConfig pec;
	
	@Autowired
	EmailSender emailSender;
	
	@Autowired
	TokenService tokenService;
	
	public User givingUser(String token) {
		User user = userRepository.findById(tokenService.decodeToken(token)).
				    orElseThrow( () -> new FunDoNotesCutomException(HttpStatus.BAD_REQUEST,"User not found"));
		return user;
	}
		
	@Override
	public User register(UserDto userDto) {
		User userEntity = new User();
		BeanUtils.copyProperties(userDto, userEntity);
		userEntity.setPassword(pec.pass().encode(userDto.getPassword()));
		User user = userRepository.save(userEntity);
		
		String token = tokenService.createToken(user.getId(), new Date(System.currentTimeMillis() + (120*1000)) );
		String body = "http://localhost:8081/verify/" + token;
		Boolean isSent = emailSender.isEmailSent(userDto.getEmail(),"Nikhilmusale70@gmail.com", "Verification email", body);
		if (!isSent) {
			throw new FunDoNotesCutomException(HttpStatus.BAD_GATEWAY,"Email not sent");
		}
		
		return user;
	}
	
	@Override
	public String login(LoginDto loginDto) {
		User user = userRepository.findByEmail(loginDto.getEmail());
		if (user == null) {
			throw new FunDoNotesCutomException(HttpStatus.BAD_REQUEST,"Email-Id incorrect");
		}
		if (!(pec.pass().matches(loginDto.getPassword(), user.getPassword()))) {
			throw new FunDoNotesCutomException(HttpStatus.BAD_REQUEST,"Password Incorrect");
		}
		
		return tokenService.createToken(user.getId());
		
	}
	
	@Override
	public void verifyEmail(String token) {
		int userId = tokenService.decodeToken(token);
		User user = userRepository.findById(userId).orElseThrow
				( () -> new FunDoNotesCutomException(HttpStatus.BAD_REQUEST,"User Not Present"));
		user.setEmailVerified(true);
		userRepository.save(user);
	}
	
	@Override
	public void forgotPassword(String email) {
		User user = userRepository.findByEmail(email);
		if(user == null)
			throw new FunDoNotesCutomException(HttpStatus.BAD_REQUEST, "Email id not found");
		
		String token = tokenService.createToken(user.getId(), new Date(System.currentTimeMillis() + (120*1000)));
		String body = token;
		Boolean isSent = emailSender.isEmailSent(user.getEmail(),"Nikhilmusale70@gmail.com", "Reset Password email", body);
		if (!isSent) {
			throw new FunDoNotesCutomException(HttpStatus.BAD_GATEWAY,"Email not sent, for forgot password");
		}
	}

	@Override
	public void resetPassword(String newPassword, String token) {
		User user = userRepository.findById(tokenService.decodeToken(token)).orElseThrow( 
					() -> new FunDoNotesCutomException(HttpStatus.BAD_REQUEST, "User Not Found, for reseting password") );
		System.out.println(user.toString());
		user.setPassword(pec.pass().encode(newPassword));
		userRepository.save(user);
	}

	@Override
	public void updatePassword(String token, UpdatePasswordDto updatePasswordDto) {
		User user = userRepository.findById(tokenService.decodeToken(token)).orElseThrow( 
				() -> new FunDoNotesCutomException(HttpStatus.BAD_REQUEST, "User Not Found, for reseting password") );
		if ( ! pec.pass().matches( updatePasswordDto.getOldPassword(), user.getPassword() ) ) {
			throw new FunDoNotesCutomException(HttpStatus.BAD_REQUEST,"Old password and new password are not matching");
		}
		user.setPassword(pec.pass().encode(updatePasswordDto.getNewPassword()));
		userRepository.save(user);
	}
	
}
