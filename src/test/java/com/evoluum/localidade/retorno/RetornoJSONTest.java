package com.evoluum.localidade.retorno;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.evoluum.localidade.dto.LocalDTO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RetornoJSON.class})
public class RetornoJSONTest {

	@Autowired
	private RetornoJSON retornoJSON;
	
	@MockBean
	private HttpServletResponse response;
	
	@Test
	public void testTransformarDados() throws IOException {
		PrintWriter printWriter = new PrintWriter("teste");
		doReturn(printWriter).when(response).getWriter();
		retornoJSON.transformarDados(new ArrayList<LocalDTO>(), response);
		printWriter.close();
		Files.delete(Paths.get("teste"));
	}
	
	@Test
	public void testTransformarDadosException() throws IOException {
		PrintWriter printWriter = new PrintWriter("teste");
		doThrow(new IOException()).when(response).getWriter();
		retornoJSON.transformarDados(new ArrayList<LocalDTO>(), response);
		printWriter.close();
		Files.delete(Paths.get("teste"));
	}
}
