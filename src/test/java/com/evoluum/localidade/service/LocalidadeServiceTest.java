package com.evoluum.localidade.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.evoluum.localidade.retorno.RetornoCSV;
import com.evoluum.localidade.retorno.RetornoFactory;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {LocalidadeServiceTest.class})
public class LocalidadeServiceTest {
	
	@SpyBean
	private LocalidadeService localidadeService;
	
	@MockBean
	private RetornoFactory retornoFactory;
	
	@MockBean
	private WSClienteService wsClienteService;
	
	@MockBean
	private HttpServletResponse response;
	
	@Test
	public void testGetTodosOsDados() {
		assertEquals(null, localidadeService.getTodosOsDados("tipo_dado_inexistente", response));
	}
	
	@Test
	public void testGetTodosOsDadosJson() {
		when(wsClienteService.getTodosOsDados()).thenReturn(null);
		assertEquals(null, localidadeService.getTodosOsDados("json", response));
	}
	
	@Test
	public void testGetTodosOsDadosCSV() {
		doReturn(null).when(localidadeService).gerarDados(any(), any(), any());
		assertEquals(null, localidadeService.getTodosOsDados("csv", response));
	}
	
	@Test
	public void testGerarDados() {
		doReturn(Optional.of(new RetornoCSV())).when(retornoFactory).getRetorno("csv");
		doReturn(new Object()).when(localidadeService).transformarDados(any(), any());
		assertNotNull(localidadeService.getTodosOsDados("csv", response));
	}
}
