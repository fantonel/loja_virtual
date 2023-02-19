package br.com.fantonel.excepts;

import java.io.Serializable;

public class ObjetoDeExcecoes implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String error;
	
	public ObjetoDeExcecoes() {}
	
	public ObjetoDeExcecoes(String code, String error) {
		super();
		this.code = code;
		this.error = error;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}