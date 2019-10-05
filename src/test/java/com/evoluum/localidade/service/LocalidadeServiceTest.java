package com.evoluum.localidade.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.evoluum.localidade.retorno.RetornoCSV;
import com.evoluum.localidade.retorno.RetornoFactory;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {LocalidadeService.class})
public class LocalidadeServiceTest {
	
	@SpyBean
	private LocalidadeService localidadeService;
	
	@MockBean
	private RetornoFactory retornoFactory;
	
	@MockBean
	private HttpServletResponse response;
	
	@MockBean
	private WSClienteService wsClienteService;
	
	@Test
	public void testGetTodosOsDados() {
		RetornoCSV retorno = Mockito.mock(RetornoCSV.class);
		doReturn(Optional.of(retorno)).when(retornoFactory).getRetorno("json");
		doNothing().when(Optional.of(retorno).get()).transformarDados(any(), any());
		localidadeService.getTodosOsDados("json", response);
	}
}
