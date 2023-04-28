package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.dto.CMRespDto;
import com.cos.photogramstart.handler.ex.CustomValidationException;

@RestController // return을 데이터로
@ControllerAdvice // exception 발생시 실행되게 만들어줌 
public class ControllerExceptionHandler {
	
	// RuntimeException발생시 실행됨
	@ExceptionHandler(CustomValidationException.class)
	public CMRespDto<?> validationException(CustomValidationException e) {
		return new CMRespDto<Map<String, String>>(-1,e.getMessage(),e.getErrorMap());
	}

}
