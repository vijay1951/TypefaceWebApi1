package com.webapi.Folder.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResponseNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;
   
	public  ResponseNotFound(String msg) {
    	   super(msg);
       }
}