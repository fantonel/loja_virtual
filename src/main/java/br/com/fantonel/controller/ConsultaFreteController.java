package br.com.fantonel.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fantonel.dto.MelhorEnvioInsereFreteRequestDto;
import br.com.fantonel.dto.MelhorEnvioInsereFreteResponseDto;
import br.com.fantonel.dto.MelhorEnvioRequestDto;
import br.com.fantonel.dto.MelhorEnvioResponseDto;
import br.com.fantonel.util.ApiIntegracao;

@RestController
@RequestMapping("api/v1/consultafrete")
public class ConsultaFreteController {
	public ConsultaFreteController() {
	}
	
	@SuppressWarnings("deprecation")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@PostMapping("/porprodutos")
	public ResponseEntity<?> consultarFreteProduto(@RequestBody @Valid MelhorEnvioRequestDto melhorEnvioRequestDto) throws IOException {
		String jsonMelhorEnvio = new ObjectMapper().writeValueAsString(melhorEnvioRequestDto);
		//
		okhttp3.OkHttpClient client01 = new okhttp3.OkHttpClient();
		okhttp3.MediaType mediaType01 = okhttp3.MediaType.parse("application/json");
		okhttp3.RequestBody body01 = okhttp3.RequestBody.create(mediaType01, jsonMelhorEnvio);
		okhttp3.Request request01 = new okhttp3.Request.Builder()
		  .url(ApiIntegracao.MELHORENVIO_SANDBOX_URL_CALCULATE).post(body01)
		  .addHeader("Accept", "application/json")
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Authorization", ApiIntegracao.MELHORENVIO_SANDBOX_TOKKEN)
		  .addHeader("User-Agent", "seu_email_suporte_tecnico@gmail.com").build();
		okhttp3.Response response01 = client01.newCall(request01).execute();
		//		
		if (response01.isSuccessful()) {
			String responseJson = response01.body().string(); 
			ObjectMapper mapper = new ObjectMapper();
			MelhorEnvioResponseDto[] dto = mapper.readValue(responseJson, MelhorEnvioResponseDto[].class);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}
		return ResponseEntity.status(HttpStatus.OK).body(new MelhorEnvioResponseDto());
	}
	
	@SuppressWarnings("deprecation")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@PostMapping("/inserirfreteprodutos")
	public ResponseEntity<?> inserirFreteProduto(@RequestBody @Valid MelhorEnvioInsereFreteRequestDto melhorEnvioInsereFreteRequestDto) throws IOException {
		String jsonMelhorEnvio = new ObjectMapper().writeValueAsString(melhorEnvioInsereFreteRequestDto);
		//		
		okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();
		okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json");
		okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, jsonMelhorEnvio);
		okhttp3.Request request = new okhttp3.Request.Builder()
		  .url(ApiIntegracao.MELHORENVIO_SANDBOX_URL_CART)
		  .post(body)
		  .addHeader("Accept", "application/json")
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Authorization", ApiIntegracao.MELHORENVIO_SANDBOX_TOKKEN)
		  .addHeader("User-Agent", "seu_email_suporte_tecnico@gmail.com")
		  .build();
		okhttp3.Response response = client.newCall(request).execute();
		//		
		if (response.isSuccessful()) {
			String responseJson = response.body().string();
			MelhorEnvioInsereFreteResponseDto dto = new ObjectMapper().readValue(responseJson, MelhorEnvioInsereFreteResponseDto.class);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}
		return ResponseEntity.status(HttpStatus.OK).body(new MelhorEnvioInsereFreteRequestDto());
	}
}