package br.com.fantonel.excepts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.fantonel.service.SendMailService;

@RestControllerAdvice
public class ControleExcecoes extends ResponseEntityExceptionHandler {
	private String emailAdm;;
	
	@Autowired
	private SendMailService sendMailService;
	
	public ControleExcecoes() {		
	}
	
	@PostConstruct
	public void init() {
		if (emailAdm == null)
			emailAdm = getEmailConfig();
	}
	
	//Exceção customizada: Monitora outras excessões, que não são capturadas pelos outros métodos.
	@ExceptionHandler({LojaVirtualExceptions.class, IllegalAccessException.class, InvocationTargetException.class})
	public ResponseEntity<Object> handleCustomException(LojaVirtualExceptions ex){
		ObjetoDeExcecoes objetoDeErroModel = new ObjetoDeExcecoes();
		objetoDeErroModel.setCode(HttpStatus.OK.toString());
		objetoDeErroModel.setError(ex.getMessage());		
		return new ResponseEntity<Object>(objetoDeErroModel, HttpStatus.OK);
	}	
	
	/*Captura exceções ocorridas no projeto*/
	@ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String msg = "";		
		if (ex instanceof MethodArgumentNotValidException) {
			List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
			for (ObjectError objE : list) {
				msg += objE.getDefaultMessage()+"\n";
			}
		}else {
			if(ex instanceof HttpRequestMethodNotSupportedException) {		
				System.out.println(ex.getMessage());
				msg = "Requisição não suporte método Get: " + ((HttpRequestMethodNotSupportedException) ex).getMessage();
			}else if(ex instanceof HttpMessageNotReadableException) {
				msg = "Não está sendo enviado dados para o corpo da requisição\n"+ex.getCause().getMessage();
			}else{
				msg = ex.getMessage();
			}
			//Envio de email de alerta ao responsável pelo projeto
			sendEmailException(ExceptionUtils.getStackTrace(ex), emailAdm);
		}
		
		//lançar exceção no console, para consulta de log no servidor.
		ex.printStackTrace();		
		//
		ObjetoDeExcecoes objetoDeErroModel = new ObjetoDeExcecoes(status.value()+" --> "+status.getReasonPhrase(), msg);
		return new ResponseEntity<Object>(objetoDeErroModel, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/*Captura exceções relacioandas ao banco de dados.*/
	@ExceptionHandler({DataIntegrityViolationException.class, ConstraintViolationException.class, SQLException.class})
	protected ResponseEntity<Object> handlerExceptionDataBase(Exception ex){
		String msg  = "";
		if (ex instanceof DataIntegrityViolationException) {
			msg = "DataBase Integrity Violation.\n"+((DataIntegrityViolationException) ex).getCause().getMessage();
		}else if (ex instanceof ConstraintViolationException) {
			msg = "Constraint Exception.\n"+ ((ConstraintViolationException) ex).getCause().getMessage();
		}else if (ex instanceof SQLException) {
			msg = "Sql Exception.\n"+ ((SQLException) ex).getCause().getMessage();
		}else{
			msg = ex.getMessage();
		}
		
		//lançar exceção no console, para consulta de log no servidor.
		ex.printStackTrace();
		
		//Envio de email de alerta ao responsável pelo projeto
		sendEmailException(ExceptionUtils.getStackTrace(ex), emailAdm);
		
		ObjetoDeExcecoes objetoDeErroModel = new ObjetoDeExcecoes(HttpStatus.INTERNAL_SERVER_ERROR.toString(), msg);
		return new ResponseEntity<Object>(objetoDeErroModel, HttpStatus.INTERNAL_SERVER_ERROR);		
	}
	
	private void sendEmailException(String mensagem, String emailDestino) {
		try {
			sendMailService.sendHtmlMail("**ERRO** Loja Virtual", mensagem, emailDestino);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
	}
	
	//Leio um arquivo texto com usuário/senha do email, apenas para não deixar fixo na aplicação,
	//uma vez que o projeto é público no GitHUb.
	private String getEmailConfig() {
		String email = "";
		String path  = "C:\\workspace\\dev\\_eclipse_workspace\\loja_virtual_except_config.txt";
		String linha = "";
		BufferedReader buffRead;
		
		try {
			buffRead = new BufferedReader(new FileReader(path));
			while (true) {
				if (linha != null) {
					email = linha;
				} else
					break;
				linha = buffRead.readLine();
			}
			buffRead.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return email;
	}
}