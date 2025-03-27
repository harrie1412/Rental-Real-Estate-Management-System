package com.javaweb.controllerAdvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaweb.model.ErrorResponseDTO;

import CustomException.FieldRequiredException;
@ControllerAdvice
//lọc dữ liệu đúng hay sai(controller)
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	 @ExceptionHandler(ArithmeticException.class)
	    public ResponseEntity<Object> handleArithmeticException(
	            ArithmeticException ex, WebRequest request) {
		 	ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		 	errorResponseDTO.setError(ex.getMessage());
		 	List<String> details = new ArrayList<String>();
		 	details.add("Số nguyên làm sao chia được cho 0!");
		 	errorResponseDTO.setDetail(details);
	        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
	 @ExceptionHandler(FieldRequiredException.class)
	    public ResponseEntity<Object> handleFieldRequiredException(
	    		FieldRequiredException ex, WebRequest request) {
		 	ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		 	errorResponseDTO.setError(ex.getMessage());//name or numberBasement is null
		 	List<String> details = new ArrayList<String>();
		 	details.add("Check lại name hoặc numberOfBasement đi bởi vì đang bị null đó!");
		 	errorResponseDTO.setDetail(details);
	        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_GATEWAY);
	    }
}
