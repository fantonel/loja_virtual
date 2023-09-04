package br.com.fantonel.service;

import java.io.Serializable;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class IgnoreHostNameSSL implements HostnameVerifier, Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final HostnameVerifier DEFAULT_HOST_NAME_VERIFIER = HttpsURLConnection.getDefaultHostnameVerifier();
	
	//Lista de possíveis certificados.
	private Set<String> trustedHosts;
	
	public IgnoreHostNameSSL(Set<String> trustedHosts) {
		this.trustedHosts = trustedHosts;
	}

	@Override
	public boolean verify(String hostName, SSLSession session) {
		//Ignoramos a verificação, se nosso host de desenvolvimento se trustedHosts o contém.
        if (trustedHosts.contains(hostName)) {
        	return true;
        }else {
        	return DEFAULT_HOST_NAME_VERIFIER.verify(hostName, session);
        }
	}	
	
	public static HostnameVerifier getDefaulHostNameVerifier() throws Exception {		
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
		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, trustManagers, new SecureRandom());		
		HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());		
		HostnameVerifier hostnameVerifier = new HostnameVerifier() {		
			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
		};
		return hostnameVerifier;		
	}
}