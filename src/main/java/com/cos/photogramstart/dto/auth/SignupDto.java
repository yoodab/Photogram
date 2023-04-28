package com.cos.photogramstart.dto.auth;


import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data // Getter, Setter
public class SignupDto {
	@Max(20)
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String email;
	@NotBlank
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
