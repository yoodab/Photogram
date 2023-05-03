package com.cos.photogramstart.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.photogramstart.config.auth.PrincipalDetails;

@Controller
public class UserController {

	@GetMapping("/user/{id}")
	public String profile(@PathVariable int id) {
		return "/user/profile";
	}
	
	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id,@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		//1. @AuthenticationPrincipal 어노테이션 활용시
		System.out.println("세션정보 : "+principalDetails.getUser());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		//2. 직접 찾는 방법
		PrincipalDetails mprinDetails = (PrincipalDetails)auth.getPrincipal();
		System.out.println("직접 찾은 세션 정보:"+mprinDetails.getUser());
		
		return "/user/update";
	}
}
