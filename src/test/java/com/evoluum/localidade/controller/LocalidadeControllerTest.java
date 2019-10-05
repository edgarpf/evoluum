package com.evoluum.localidade.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.evoluum.localidade.dto.MunicipioDTO;
import com.evoluum.localidade.service.LocalidadeService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {LocalidadeController.class})
public class LocalidadeControllerTest {
	
	@Autowired
	private LocalidadeController localidadeController;
	
	@MockBean
	private LocalidadeService localidadeService;
	
	@MockBean
	private HttpServletResponse response;
	
	@Test
	public void testGetTodosOsDados404() {
		doNothing().when(localidadeService).getTodosOsDados("tipo_dado_inexistente", response);
		assertEquals(HttpStatus.NOT_FOUND, localidadeController.getTodosOsDados("tipo_dado_inexistente", response).getStatusCode());
	}
	
	@Test
	public void testGetTodosOsDados() {
		when(response.getContentType()).thenReturn("application/json");
		doNothing().when(localidadeService).getTodosOsDados("json", response);
		assertEquals(HttpStatus.OK, localidadeController.getTodosOsDados("json", response).getStatusCode());
	}
	
	@Test
	public void getIdMunicipio404() {
		when(localidadeService.getIdMunicipio("cidade_teste")).thenReturn(new ArrayList<MunicipioDTO>());
		assertEquals(HttpStatus.OK, localidadeController.getIdMunicipio("cidade_teste").getStatusCode());
	}
}
