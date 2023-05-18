package com.cos.photogramstart.web;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;
	
	@GetMapping("/user/{pageUserId}")
	public String profile(@PathVariable int pageUserId, Model model,@AuthenticationPrincipal PrincipalDetails principalDetails) {
		UserProfileDto dto = userService.회원프로필(pageUserId, principalDetails.getUser().getId());
		model.addAttribute("dto",dto);
		return "/user/profile";
	}
	
	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id,@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		//1. @AuthenticationPrincipal 어노테이션 활용시 추천
//		System.out.println("세션정보 : "+principalDetails.getUser());

		
		//2. 직접 찾는 방법 별로임
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		PrincipalDetails mprinDetails = (PrincipalDetails)auth.getPrincipal();
//		System.out.println("직접 찾은 세션 정보:"+mprinDetails.getUser());
		
		return "/user/update";
	}
}
