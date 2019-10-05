package com.evoluum.localidade.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.evoluum.localidade.dto.Estado;
import com.evoluum.localidade.dto.Mesorregiao;
import com.evoluum.localidade.dto.Microrregiao;
import com.evoluum.localidade.dto.Municipio;
import com.evoluum.localidade.dto.Regiao;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {WSClienteService.class})
public class WSClienteServiceTest {
	
	private static final int ESTADO_ID = 1;
	private static final String ESTADO_NOME = "nome estado";
	
	@SpyBean
	private WSClienteService wsClienteService;
	
	@MockBean
    private RestTemplate restTemplate;
	
	@Value("${endpoint.estados}")
	private String estadosEndpoint;

	@Value("${endpoint.municipios}")
	private String municipiosEndpoint;
	
	@Value("${endpoint.todosMunicipios}")
	private String todosMunicipiosEndpoint;
	
	@Test
	public void testGetTodosOsDados() {
		Estado[] arrayEstado = new Estado[1];
		arrayEstado[0] = gerarEstado();
		ResponseEntity<Estado[]> responseEntityEstado = new ResponseEntity<Estado[]>(arrayEstado, HttpStatus.OK);
		when(restTemplate.getForEntity(estadosEndpoint, Estado[].class)).thenReturn(responseEntityEstado);
		
		Municipio[] arrayMunicipio = new Municipio[1];
		arrayMunicipio [0] = gerarMunicipio();
		ResponseEntity<Municipio[]> responseEntityMunicipio = new ResponseEntity<Municipio[]>(arrayMunicipio, HttpStatus.OK);
		when(restTemplate.getForEntity(String.format(municipiosEndpoint, ESTADO_ID), Municipio[].class)).thenReturn(responseEntityMunicipio);
			
		assertNotNull(wsClienteService.getTodosOsDados());
	}

	private Municipio gerarMunicipio() {
		Municipio municipio = new Municipio();
		Microrregiao microrregiao = new Microrregiao();
		Mesorregiao mesorregiao = new Mesorregiao();
		
		mesorregiao.setNome("mesorregiao teste");
		microrregiao.setMesorregiao(mesorregiao);
		
		municipio.setMicrorregiao(microrregiao);
		municipio.setNome(ESTADO_NOME);
		
		return municipio;
	}

	private Estado gerarEstado() {
		Estado estado = new Estado();
		estado.setId(ESTADO_ID);
		Regiao regiao = new Regiao();
		
		regiao.setNome("regiao teste");
		estado.setRegiao(regiao);
		
		return estado;
	}
	
	@Test
	public void testGetIdMunicipio() {
		Municipio[] arrayMunicipio = new Municipio[1];
		arrayMunicipio[0] = gerarMunicipio();
		ResponseEntity<Municipio[]> responseEntityMunicipio= new ResponseEntity<Municipio[]>(arrayMunicipio, HttpStatus.OK);
		when(restTemplate.getForEntity(todosMunicipiosEndpoint, Municipio[].class)).thenReturn(responseEntityMunicipio);	
		assertNotNull(wsClienteService.getIdMunicipio(ESTADO_NOME));
	}
}
