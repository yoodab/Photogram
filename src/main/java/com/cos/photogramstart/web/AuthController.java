package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 필드를 DI할때 사용
@Controller // 1.IoC 2. 파일을 리턴하는 컨트롤러
public class AuthController {

	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	private final AuthService authService;

	/*
	 * public AuthController(AuthService authService) { this.authService =
	 * authService; }
	 */

	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}

	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}

	@PostMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {
		// key=value (w-xxx-form-urlencoded)
		// 위 처럼 일반 자료형/Dto로 받으면 xxx형태의 데이터를 받을 수 있음
		
			// User <- SignupDto
			User user = signupDto.toEntity();
			authService.회원가입(user);

			
			// 로그를 남기는 후처리가 들어감
			return "auth/signin";
		

	}
}
