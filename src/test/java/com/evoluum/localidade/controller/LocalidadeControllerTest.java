package com.evoluum.localidade.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

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
		when(localidadeService.getTodosOsDados("tipo_dado_inexistente", response)).thenReturn(null);
		assertEquals(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null).getStatusCode(), localidadeController.getTodosOsDados("tipo_dado_inexistente", response).getStatusCode());
	}
	
	@Test
	public void testGetTodosOsDados() {
		when(localidadeService.getTodosOsDados("json", response)).thenReturn(new Object());
		assertEquals(ResponseEntity.status(HttpStatus.OK).body(null).getStatusCode(), localidadeController.getTodosOsDados("json", response).getStatusCode());
	}
}
