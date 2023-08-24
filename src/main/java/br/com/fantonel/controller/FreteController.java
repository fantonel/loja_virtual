package br.com.fantonel.controller;

import java.io.IOException;
import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fantonel.dto.CancelOrderDto;
import br.com.fantonel.dto.EtiquetaDto;
import br.com.fantonel.dto.EtiquetarModeDto;
import br.com.fantonel.dto.MelhorEnvioCompraFreteOrdersDto;
import br.com.fantonel.dto.MelhorEnvioInsereFreteRequestDto;
import br.com.fantonel.dto.MelhorEnvioInsereFreteResponseDto;
import br.com.fantonel.dto.MelhorEnvioPurchaseResponseDto;
import br.com.fantonel.dto.MelhorEnvioRequestDto;
import br.com.fantonel.dto.MelhorEnvioResponseDto;
import br.com.fantonel.dto.MelhorEnvioTracking;
import br.com.fantonel.excepts.LojaVirtualExceptions;
import br.com.fantonel.util.ApiIntegracao;

@RestController
@RequestMapping("api/v1/frete")
public class FreteController {
	
	public FreteController() {
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
		String responseJson = response01.body().string();
		if (response01.isSuccessful()) {						
			ObjectMapper mapper = new ObjectMapper();
			MelhorEnvioResponseDto[] dto = mapper.readValue(responseJson, MelhorEnvioResponseDto[].class);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}
		System.err.println(responseJson);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível consultar as informações de frete!");
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
		String responseJson = response.body().string();
		if (response.isSuccessful()) {
			
			MelhorEnvioInsereFreteResponseDto dto = new ObjectMapper().readValue(responseJson, MelhorEnvioInsereFreteResponseDto.class);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}
		System.err.println(responseJson);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível inserir o frete solicitado!");
	}
	
	@SuppressWarnings("deprecation")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@PostMapping("/comprarfrete")
	public ResponseEntity<?> comprarFrete(@RequestBody @Valid MelhorEnvioCompraFreteOrdersDto melhorEnvioCompraFreteOrdersDto) throws IOException {
		String jsonMelhorEnvio = new ObjectMapper().writeValueAsString(melhorEnvioCompraFreteOrdersDto);
		//
		okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();
		okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json");
		okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, jsonMelhorEnvio);
		okhttp3.Request request = new okhttp3.Request.Builder()
		  .url(ApiIntegracao.MELHORENVIO_SANDBOX_URL_CHECKOUT)
		  .post(body)
		  .addHeader("Accept", "application/json")
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Authorization", ApiIntegracao.MELHORENVIO_SANDBOX_TOKKEN)
		  .addHeader("User-Agent", "seu_email_suporte_tecnico@gmail.com")
		  .build();
		//
		okhttp3.Response response = client.newCall(request).execute();
		String responseJson = response.body().string();
		if (response.isSuccessful()) {			
			MelhorEnvioPurchaseResponseDto dto = new ObjectMapper().readValue(responseJson, MelhorEnvioPurchaseResponseDto.class);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}
		System.err.println(responseJson);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("As informações de rastreamento ainda não estão disponíveis. Tente novamente mais tarde!");
	}
	
	@SuppressWarnings("deprecation")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@PostMapping("/geraretiquetas")
	public ResponseEntity<?> gerarEtiqueta(@RequestBody @Valid EtiquetaDto orders) throws IOException, LojaVirtualExceptions {
		String jsonMelhorEnvio = new ObjectMapper().writeValueAsString(orders);
		//
		okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();
		okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json");
		okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, jsonMelhorEnvio);
		okhttp3.Request request = new okhttp3.Request.Builder()
		  .url(ApiIntegracao.MELHORENVIO_SANDBOX_URL_TAG)
		  .post(body)
		  .addHeader("Accept", "application/json")
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Authorization", ApiIntegracao.MELHORENVIO_SANDBOX_TOKKEN)
		  .addHeader("User-Agent", "seu_email_suporte_tecnico@gmail.com")
		  .build();
		//
		okhttp3.Response response = client.newCall(request).execute();
		String responseJson = response.body().string();
		if (response.isSuccessful()) {			
			JsonNode jsonNode = new ObjectMapper().readTree(responseJson);
			Iterator<JsonNode> it = jsonNode.iterator();
			while (it.hasNext()) {
				JsonNode node = it.next();
				if (node.get("status").booleanValue() == true ) {
					String message = node.get("message").textValue();
					if (!message.contains("Envio gerado com sucesso")) {
						System.err.println(message);
						throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, message);
					}
				}else {
					System.err.println(node.get("message").textValue());
					throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, node.get("message").textValue());
				}
			}
			return ResponseEntity.status(HttpStatus.OK).body(responseJson);
		}
		System.err.println(responseJson);
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "Não foi possível gerar as etiquetas, dos produtos vinculados ao pedido!");
	}
	
	@SuppressWarnings("deprecation")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@PostMapping("/obterurletiqueta")
	public ResponseEntity<?> obterurletiqueta(@RequestBody @Valid EtiquetarModeDto orders) throws IOException, LojaVirtualExceptions {
		String jsonMelhorEnvio = new ObjectMapper().writeValueAsString(orders);
		//
		okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();
		okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json");
		okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, jsonMelhorEnvio);
		okhttp3.Request request = new okhttp3.Request.Builder()
				.url(ApiIntegracao.MELHORENVIO_SANDBOX_URL_TAG_PRINT)
		  .post(body)
		  .addHeader("Accept", "application/json")
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Authorization", ApiIntegracao.MELHORENVIO_SANDBOX_TOKKEN)
		  .addHeader("User-Agent", "seu_email_suporte_tecnico@gmail.com")
		  .build();
		//
		okhttp3.Response response = client.newCall(request).execute();
		String responseJson = response.body().string();
		JsonNode jsonNode = new ObjectMapper().readTree(responseJson);
		if (jsonNode.get("errors") != null) {
			System.err.println(jsonNode.get("message").textValue());
			throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, jsonNode.get("message").textValue());
		}
		if (response.isSuccessful()) {
			return ResponseEntity.status(HttpStatus.OK).body(responseJson);
		}
		System.err.println(responseJson);
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "Ocorreu um erro ao gerar a url de impressão das etiquetas!");
	}
	
	@SuppressWarnings("deprecation")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/consultacancelamanentoetiqueta")
	public ResponseEntity<?> consultaCancemanentoEtiqueta(@RequestBody @Valid EtiquetarModeDto orders) throws IOException, LojaVirtualExceptions {
		String jsonMelhorEnvio = new ObjectMapper().writeValueAsString(orders);
		//
		okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();
		//
		okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json");
		okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, jsonMelhorEnvio);
		okhttp3.Request request = new okhttp3.Request.Builder()
		  .url(ApiIntegracao.MELHORENVIO_SANDBOX_URL_TAG_CANCELLABLE)
		  .post(body)
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Accept", "application/json")
		  .addHeader("Authorization", ApiIntegracao.MELHORENVIO_SANDBOX_TOKKEN)
		  .addHeader("User-Agent", "seu_email_suporte_tecnico@gmail.com")
		  .build();
		okhttp3.Response response = client.newCall(request).execute();
		//
		if (response.isSuccessful()) {
			return ResponseEntity.status(HttpStatus.OK).body(response);			
		}
		System.err.println(response.body().string());
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "Ocorreu um problema, ao verificar se a etiqueta pode ser cancelada!");
	}
	
	@SuppressWarnings("deprecation")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/cancelaretiqueta")
	public ResponseEntity<?> cancelaretiqueta(@RequestBody @Valid CancelOrderDto orders) throws IOException, LojaVirtualExceptions {
		//Verifico, primeiramente, se a etiqueta pode ser cancelada.
		EtiquetarModeDto etiqueta = new EtiquetarModeDto();
		etiqueta.setOrders(new String[]{orders.getOrders().getId()});
		ResponseEntity<?> responseCancellable = consultaCancemanentoEtiqueta(etiqueta);
		if (responseCancellable.getStatusCode() == HttpStatus.OK) {
			String jsonCancellable = new ObjectMapper().writeValueAsString(responseCancellable.getBody());
			JsonNode treeNode = new ObjectMapper().readTree(jsonCancellable);
			boolean successful = treeNode.get("successful").booleanValue();
			if (!successful)
				throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, treeNode.get("uncancellable_reason").textValue());
			//Realiza o cancelamento, uma vez que seja possível.
			String jsonOrders = new ObjectMapper().writeValueAsString(orders);
			okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();
			okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json");
			okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, jsonOrders);
			okhttp3.Request request = new okhttp3.Request.Builder()
			  .url(ApiIntegracao.MELHORENVIO_SANDBOX_URL_TAG_CANCEL)
			  .post(body)
			  .addHeader("Content-Type", "application/json")
			  .addHeader("Accept", "application/json")
			  .addHeader("Authorization", ApiIntegracao.MELHORENVIO_SANDBOX_TOKKEN)
			  .addHeader("User-Agent", "seu_email_suporte_tecnico@gmail.com")
			  .build();
			okhttp3.Response response = client.newCall(request).execute();
			//
			String responseJson = response.body().string();
			if (response.isSuccessful()) {
				return ResponseEntity.status(HttpStatus.OK).body(responseJson);			
			}else {
				System.err.println(responseJson);
				throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "Não é possível cancelar a etiqueta da ordem de compra!");
			}
		}
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "Ocorreu um problema, ao verificar se a etiqueta pode ser cancelada!");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/consultatransportadora/{companyId}")
	public ResponseEntity<?> consultarTransporadora(@PathVariable int companyId) throws IOException, LojaVirtualExceptions {
		okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();
		//
		okhttp3.Request request = new okhttp3.Request.Builder()
		  .url(ApiIntegracao.MELHORENVIO_SANDBOX_COMPANIES+"/"+companyId)
		  .get()
		  .addHeader("accept", "application/json")
		  .addHeader("User-Agent", "seu_email_suporte_tecnico@gmail.com")
		  .build();
		okhttp3.Response response = client.newCall(request).execute();
		//
		String responseJson = response.body().string();
		if (response.isSuccessful()) {
			return ResponseEntity.status(HttpStatus.OK).body(responseJson);			
		}
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "Transportadora "+companyId+" não foi encontrada!");
	}
	
	@SuppressWarnings("deprecation")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/rastreiopedido")
	public ResponseEntity<?> rastrearPedido(@RequestBody @Valid EtiquetaDto orders) throws IOException, LojaVirtualExceptions {
		String jsonMelhorEnvio = new ObjectMapper().writeValueAsString(orders);
		//
		okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();
		okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json");
		//okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "{\"orders\":[\"99f14820-a46f-489d-b056-0acafabed574\"]}");
		okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, jsonMelhorEnvio);
		okhttp3.Request request = new okhttp3.Request.Builder()
		  //.url("https://sandbox.melhorenvio.com.br/api/v2/me/shipment/tracking")
 		  .url(ApiIntegracao.MELHORENVIO_SANDBOX_URL_TAG_TRACKING)
		  .post(body)
		  .addHeader("Accept", "application/json")
		  .addHeader("Content-type", "application/json")
		  .addHeader("Authorization", ApiIntegracao.MELHORENVIO_SANDBOX_TOKKEN)
		  .addHeader("User-Agent", "seu_email_suporte_tecnico@gmail.com")
		  .build();
		okhttp3.Response response = client.newCall(request).execute();
		if (response.isSuccessful()) {
			String responseStr = response.body().string();
			MelhorEnvioTracking meTracking = new MelhorEnvioTracking();
			JsonNode root = new ObjectMapper().readTree(responseStr);
			Iterator<JsonNode> it = root.iterator();
			while (it.hasNext()) {
				JsonNode node = it.next();
				meTracking.setId(node.get("id").textValue());
				meTracking.setProtocol(node.get("protocol").textValue());
				meTracking.setStatus(node.get("status").textValue());
				meTracking.setTracking(node.get("tracking").textValue());
				meTracking.setMelhorEnvioTracking(node.get("melhorenvio_tracking").textValue());
				meTracking.setCreatedAt(node.get("created_at").textValue());
				meTracking.setPaidAt(node.get("paid_at").textValue());
				meTracking.setGeneratedAt(node.get("generated_at").textValue());
				meTracking.setPostedAt(node.get("posted_at").textValue());
				meTracking.setDeliveredAt(node.get("delivered_at").textValue());
				meTracking.setCanceledAt(node.get("canceled_at").textValue());
				meTracking.setExpiredAt(node.get("expired_at").textValue());
			}
			return ResponseEntity.status(HttpStatus.OK).body(meTracking);
		}
		System.err.println(response.body().string());
		throw new LojaVirtualExceptions(HttpStatus.NOT_FOUND, "Ocorreu um problema, ao rastrear o pedido! Tente novamente em alguns minutos");
	}
}