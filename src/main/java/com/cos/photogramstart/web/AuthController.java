package com.cos.photogramstart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.dto.auth.SignupDto;

@Controller
public class AuthController {

	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	
	@PostMapping("/auth/signup")
	public String signup(SignupDto signupDto) { 
		// key=value (w-xxx-form-urlencoded) 
		// 위 처럼 일반 자료형/Dto로 받으면 xxx형태의 데이터를 받을 수 있음
		
		log.info(signupDto.toString());
		System.out.println("================================");
		System.out.println("나 실행됨?");
		return "auth/signin";
	}
}
