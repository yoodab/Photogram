package com.cos.photogramstart.dto.auth;


import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data // Getter, Setter
public class SignupDto {
	private String username;
	private String password;
	private String email;
	private String name;
	
	// Dto에 맞는 객체 만드는데 사용함
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.build();
	}
}
