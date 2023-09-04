package br.com.fantonel.service;

import java.io.Serializable;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.glassfish.jersey.media.multipart.internal.MultiPartWriter;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

public class HostIgnoringClient implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String hostName;	
	
	public HostIgnoringClient(String hostName) {
		this.hostName = hostName;
	}
	
	public Client hostIgnoringClient () throws Exception {
		//Ignorar a autenticação, meio que realizando a simulação da criação de um certificado SSL fantasma.
		//Dessa forma poderemos realizar a integração, p.e, com a  API de pagamento durante o desenvolvimento.
		TrustManager[] trustManagers = new TrustManager[] {
			new X509TrustManager() {
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				@Override
				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}
				@Override
				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}
			} 
		};
		//Inicialização do SSL Context, de protocolo TLS.
		SSLContext sslContext = SSLContext.getInstance("TLS");
		//Nesse local seria exigido o certificado, arquivo pago/oficial do site, mas passaremos NULL a variável, para então: 
		//em substituição, passaremos nosso certificado fantasma (trustManagers).
		sslContext.init(null, trustManagers, new SecureRandom());
		//
		Set<String> hostNameList = new HashSet<String>();
		hostNameList.add(this.hostName);
		HttpsURLConnection.setDefaultHostnameVerifier(new IgnoreHostNameSSL(hostNameList));
		HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

		DefaultClientConfig config = new DefaultClientConfig();
		Map<String, Object> properties = config.getProperties();
		HTTPSProperties httpsProperties = new HTTPSProperties(new HostnameVerifier() {
			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
		}, sslContext);

		properties.put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, httpsProperties);
		//Para utilização na integração com arquivo Json
		config.getClasses().add(JacksonJsonProvider.class);
		//Para realização de Upload de arquivos/imagens entre APIs, caso necessário.
		config.getClasses().add(MultiPartWriter.class);

		return Client.create(config);		
	}
}