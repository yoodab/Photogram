package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // 해당파일로 시큐리티 활성화
@Configuration // IoC등록
public class SecurityConfig {

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
			.antMatchers("/", "/user/**", "/image/**", "/subscribe/**, /comment/**").authenticated()
			// 위에 주소말고는 다 허용함
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/auth/signin")
			.defaultSuccessUrl("/");
		return http.build();
	}
	
}
