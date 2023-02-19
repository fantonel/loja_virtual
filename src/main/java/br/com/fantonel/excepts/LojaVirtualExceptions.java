package br.com.fantonel.excepts;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class LojaVirtualExceptions extends Exception implements Serializable{
	private static final long serialVersionUID = 1L;

	public LojaVirtualExceptions(String errorMessage) {
		super(errorMessage);
	}
	
	public LojaVirtualExceptions(String code, String errorMessage) {
		super(code+" : "+errorMessage);
	}
	
	public LojaVirtualExceptions(HttpStatus httpStatus, String errorMessage) {
		super(httpStatus.toString()+" : "+errorMessage);
	}
}