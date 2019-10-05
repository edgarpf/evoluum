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

import com.evoluum.localidade.dto.EstadoDTO;
import com.evoluum.localidade.dto.Mesorregiao;
import com.evoluum.localidade.dto.Microrregiao;
import com.evoluum.localidade.dto.MunicipioDTO;
import com.evoluum.localidade.dto.Regiao;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {WSClienteService.class})
public class WSClienteServiceTest {
	
	private static final int ESTADO_ID = 1;
	
	@SpyBean
	private WSClienteService wsClienteService;
	
	@MockBean
    private RestTemplate restTemplate;
	
	@Value("${endpoint.estados}")
	private String estadosEndpoint;

	@Value("${endpoint.municipios}")
	private String municipiosEndpoint;
	
	@Test
	public void testGetTodosOsDados() {
		EstadoDTO[] arrayEstadoDTO = new EstadoDTO[1];
		arrayEstadoDTO[0] = gerarEstadoDTO();
		ResponseEntity<EstadoDTO[]> responseEntityEstadoDTO = new ResponseEntity<EstadoDTO[]>(arrayEstadoDTO, HttpStatus.OK);
		when(restTemplate.getForEntity(estadosEndpoint, EstadoDTO[].class)).thenReturn(responseEntityEstadoDTO);
		
		MunicipioDTO[] arrayMunicipioDTO = new MunicipioDTO[1];
		arrayMunicipioDTO [0] = gerarMunicipioDTO();
		ResponseEntity<MunicipioDTO[]> responseEntityMunicipioDTO = new ResponseEntity<MunicipioDTO[]>(arrayMunicipioDTO, HttpStatus.OK);
		when(restTemplate.getForEntity(String.format(municipiosEndpoint, ESTADO_ID), MunicipioDTO[].class)).thenReturn(responseEntityMunicipioDTO);
			
		assertNotNull(wsClienteService.getTodosOsDados());
	}

	private MunicipioDTO gerarMunicipioDTO() {
		MunicipioDTO municipioDTO = new MunicipioDTO();
		Microrregiao microrregiao = new Microrregiao();
		Mesorregiao mesorregiao = new Mesorregiao();
		
		mesorregiao.setNome("mesorregiao teste");
		microrregiao.setMesorregiao(mesorregiao);
		
		municipioDTO.setMicrorregiao(microrregiao);
		
		return municipioDTO;
	}

	private EstadoDTO gerarEstadoDTO() {
		EstadoDTO estadoDTO = new EstadoDTO();
		estadoDTO.setId(ESTADO_ID);
		Regiao regiao = new Regiao();
		
		regiao.setNome("regiao teste");
		estadoDTO.setRegiao(regiao);
		
		return estadoDTO;
	}
}
