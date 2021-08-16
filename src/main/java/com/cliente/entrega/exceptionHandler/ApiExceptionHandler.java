package com.cliente.entrega.exceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cliente.entrega.exception.ServiceException;

@ControllerAdvice // Essa anotação, diz que a classe é um componente Spring, com o propósito de tratar todas exceções de modo global, para todos os controlladores da applicação
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Error.Campo> campos = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			campos.add(new Error.Campo(nome, mensagem));
		}
		
		Error error = new Error();
		error.setStatus(status.value());
		error.setDataHora(LocalDateTime.now());
		error.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente");
		error.setCampos(campos);
		
		return handleExceptionInternal(ex, error, headers, status, request);
	}
	
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<Object> handServiceException(ServiceException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Error error = new Error();
		error.setStatus(status.value());
		error.setDataHora(LocalDateTime.now());
		error.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
	}
}
