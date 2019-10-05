package com.evoluum.localidade.retorno;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.evoluum.localidade.dto.LocalDTO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RetornoCSV.class})
public class RetornoCSVTest {

	@SpyBean
	private RetornoCSV retornoCSV;
	
	@MockBean
	private HttpServletResponse response;
	
	@MockBean
	private ServletOutputStream servletOutputStream;
	
	@Test
	public void testTransformarDados() throws IOException {
		doNothing().when(retornoCSV).gerarAquivo(any(), any());;
		retornoCSV.transformarDados(new ArrayList<LocalDTO>(), response);
	}
	
	@Test
	public void testTransformarDadosException() throws IOException {
		doThrow(new IOException()).when(retornoCSV).gerarAquivo(any(), any());
		retornoCSV.transformarDados(new ArrayList<LocalDTO>(), response);
	}
	
	@Test
	public void testGerarAquivo() throws IOException {
		doReturn(servletOutputStream).when(response).getOutputStream();
		retornoCSV.gerarAquivo(response, new StringBuilder("texto_teste"));
	}
}
