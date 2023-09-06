package br.com.fantonel.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tokenapis")
public class TokensApis implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@Column(name = "apinome", nullable = false)
	private String apiNome;
	
	@Column(name = "apitokentype", nullable = false)
	private String apiTokenType;
	
	@Column(name = "apiaccesstoken", nullable = false)
	private String apiAccessToken;
	
	@Column(name = "apiusername", nullable = false)
	private String apiUserName;
	
	@Column(name = "apiscope", nullable = false)
	private String apiScope;
	
	@Column(name = "apijti", nullable = false)
	private String apiJti;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "apidatahora", nullable = false, updatable = false)
	private Date apiDataHora = Calendar.getInstance().getTime();
	
	@Column(name = "apiexpiresin", nullable = false)
	private Integer apiExpiresIn;

	public TokensApis() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getApiNome() {
		return apiNome;
	}

	public void setApiNome(String apiNome) {
		this.apiNome = apiNome;
	}

	public String getApiTokenType() {
		return apiTokenType;
	}

	public void setApiTokenType(String apiTokenType) {
		this.apiTokenType = apiTokenType;
	}

	public String getApiAccessToken() {
		return apiAccessToken;
	}

	public void setApiAccessToken(String apiAccessToken) {
		this.apiAccessToken = apiAccessToken;
	}

	public String getApiUserName() {
		return apiUserName;
	}

	public void setApiUserName(String apiUserName) {
		this.apiUserName = apiUserName;
	}

	public String getApiScope() {
		return apiScope;
	}

	public void setApiScope(String apiScope) {
		this.apiScope = apiScope;
	}

	public String getApiJti() {
		return apiJti;
	}

	public void setApiJti(String apiJti) {
		this.apiJti = apiJti;
	}

	public Date getApiDataHora() {
		return apiDataHora;
	}

	public void setApiDataHora(Date apiDataHora) {
		this.apiDataHora = apiDataHora;
	}

	public Integer getApiExpiresIn() {
		return apiExpiresIn;
	}

	public void setApiExpiresIn(Integer apiExpiresIn) {
		this.apiExpiresIn = apiExpiresIn;
	}
	
	/**
	 *  
	 * @return true Se token tem 55 minutos 55 e 60 minutos está para expirar.
	 */
	public boolean hasExpired() {
		Date dataAtual = Calendar.getInstance().getTime();		
		Long tempo = dataAtual.getTime() - this.apiDataHora.getTime();
		Long minutos = (tempo / 1000) / 60;
		
		return minutos.intValue() < 55 ? false : true;	
	}

	@Override
	public String toString() {
		return "TokensApis [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokensApis other = (TokensApis) obj;
		return Objects.equals(id, other.id);
	}	
}