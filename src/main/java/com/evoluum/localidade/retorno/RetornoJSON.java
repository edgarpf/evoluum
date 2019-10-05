package com.evoluum.localidade.retorno;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.evoluum.localidade.dto.LocalDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RetornoJSON implements Retorno {

	private Logger logger = LoggerFactory.getLogger(RetornoJSON.class);
	
	@Override
	public void transformarDados(List<LocalDTO> list, HttpServletResponse response) {
		try {
			logger.info("Gerando JSON.");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(new ObjectMapper().writeValueAsString(list));
			logger.info("JSON gerado com sucesso.");
		} catch (IOException e) {
			logger.info("Falha na geração do JSON.");
			logger.error(e.getMessage());
		}	
	}
}
