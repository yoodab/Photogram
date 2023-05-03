package com.cos.photogramstart.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;

@RestController // return을 데이터로
@ControllerAdvice // exception 발생시 실행되게 만들어줌 
public class ControllerExceptionHandler {
	
	// RuntimeException발생시 실행됨
	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		return Script.back(e.getErrorMap().toString());
	}
	
	// RuntimeException발생시 실행됨
		@ExceptionHandler(CustomValidationApiException.class)
		public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
			return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),e.getErrorMap()), HttpStatus.BAD_REQUEST);
		}
		
		@ExceptionHandler(CustomApiException.class)
		public ResponseEntity<?> apiException(CustomApiException e) {
			return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),null), HttpStatus.BAD_REQUEST);
		}
	
	// CMRespDto, Script 비교 
	// 1. 클라이언트에게 응답할 때는 Script
	// 2. Ajax통신 - CMRespDto
	// 3. Android통신 - CMRespDto
}
