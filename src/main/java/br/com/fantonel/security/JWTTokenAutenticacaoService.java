package br.com.fantonel.security;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.fantonel.ApplicationContextLoad;
import br.com.fantonel.model.Usuario;
import br.com.fantonel.repository.UsuarioRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

/*Criar a autenticação e retonar também a autenticação JWT*/
@Service
@Component
public class JWTTokenAutenticacaoService {	
	/*Token de validade*/
	/*1 dia = 86400000 milisegundos --> 7 x 86400000 = 604800000*/
	private static final long EXPIRATION_TIME = 604800000;
	
	/*Chave de senha para juntar com o JWT*/
	private static final String SECRET = "ss/-*-*sds565dsd-s/d-s*dsds";
	
	private static final String TOKEN_PREFIX = "Bearer";
	
	private static final String HEADER_STRING = "Authorization";
	
	/*Gera o token e da a responsta para o cliente o com JWT*/
	public void addAuthentication(HttpServletResponse response, String username) throws Exception {		
		/*Montagem do Token*/		
		String JWT = Jwts.builder()./*Chama o gerador de token*/
				setSubject(username) /*Adiciona o user*/
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact(); /*Temp de expiração*/
		
		/*Exe: Bearer *-/a*dad9s5d6as5d4s5d4s45dsd54s.sd4s4d45s45d4sd54d45s4d5s.ds5d5s5d5s65d6s6d*/
		String token = TOKEN_PREFIX + " " + JWT;
		
		/*Dá a resposta pra tela e para o cliente, outra API, navegador, aplicativo, javascript, outra chamadajava*/
		response.addHeader(HEADER_STRING, token);
		
		liberacaoCors(response);
		
		/*Usado para ver no Postman para teste*/
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");		
	}	
	
	/*Retorna o usuário validado com token ou caso nao seja valido retona null*/
	public Authentication getAuthetication(HttpServletRequest request, HttpServletResponse response) throws IOException {		
		String token = request.getHeader(HEADER_STRING);		
		try {
			if (token != null) {			
				String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();			
				/*Faz a validacao do token do usuário na requisicao e obtem o USER*/
				String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(tokenLimpo).getBody().getSubject(); /*ADMIN ou fantonel*/				
				if (user != null) {				
					Optional<Usuario> usuarioModel = ApplicationContextLoad.getApplicationContext().getBean(UsuarioRepository.class).findByLogin(user);					
					if (usuarioModel != null) {
						if(usuarioModel.isPresent()) {
							return new UsernamePasswordAuthenticationToken(usuarioModel.get().getLogin(),usuarioModel.get().getSenha(),usuarioModel.get().getAuthorities());
						}
					}
				}
			}
		}catch (SignatureException e) {
			response.getWriter().write("O token está inválido!!!");			
		}catch (ExpiredJwtException e) {
			response.getWriter().write("A sua sessão expirou. Faça um novo login!!!");			
		}finally {
			liberacaoCors(response);
		}
		return null;
	}	
	
	/*Fazendo liberação contra erro de COrs no navegador*/
	private void liberacaoCors(HttpServletResponse response) {		
		if (response.getHeader("Access-Control-Allow-Origin") == null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
		}
		
		if (response.getHeader("Access-Control-Allow-Headers") == null) {
			response.addHeader("Access-Control-Allow-Headers", "*");
		}		
		
		if (response.getHeader("Access-Control-Request-Headers") == null) {
			response.addHeader("Access-Control-Request-Headers", "*");
		}
		
		if (response.getHeader("Access-Control-Allow-Methods") == null) {
			response.addHeader("Access-Control-Allow-Methods", "*");
		}		
	}
}