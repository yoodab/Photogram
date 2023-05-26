package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cos.photogramstart.config.oauth.OAuth2DetailsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity // 해당파일로 시큐리티 활성화
@Configuration // IoC등록
public class SecurityConfig {

	private final OAuth2DetailsService oAuth2DetailsService;
	
	@Bean
	BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception{
		http.csrf().disable();
		// 
		http.authorizeRequests()
			// 아래 주소들로 시작하면 인증이 필요함
			.antMatchers("/", "/user/**", "/image/**", "/subscribe/**, /comment/**","/api/**").authenticated()
			// 위에 주소말고는 다 허용함
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/auth/signin") // GET요청시
			.loginProcessingUrl("/auth/signin") // POST -> 스프링 시큐리티가 로그인 프로세스 진행 -> UserDetailsService
			.defaultSuccessUrl("/")
			.and()
			.oauth2Login() // form로그인도 하는데, oauth2 로그인도 사용함
			.userInfoEndpoint() // oauth2 로그인을 하면 최종 응답을 회원 정보로 받을 수 있다.
			.userService(oAuth2DetailsService);
		return http.build();
	}
	
}
