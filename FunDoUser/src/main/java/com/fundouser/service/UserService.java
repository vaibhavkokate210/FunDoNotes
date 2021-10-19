package com.fundouser.service;

import org.springframework.stereotype.Service;
import com.fundouser.dto.LoginDto;
import com.fundouser.dto.UpdatePasswordDto;
import com.fundouser.dto.UserDto;
import com.fundouser.entity.User;

@Service
public interface UserService {
	
	public User register(UserDto userDto);
	public String login(LoginDto loginDto);
	public void verifyEmail(String token);
	public void forgotPassword(String email);
	public void resetPassword(String newPassword, String token);
	public void updatePassword(String token, UpdatePasswordDto updatePasswordDto);
}
