package com.evoluum.localidade.service;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evoluum.localidade.retorno.Retorno;
import com.evoluum.localidade.retorno.RetornoFactory;

@Service
public class LocalidadeService {	
	
	private Logger logger = LoggerFactory.getLogger(LocalidadeService.class);
	
	@Autowired
	private RetornoFactory retornoFactory;
	
	@Autowired
	private WSClienteService wsClienteService;
	
	public Object getTodosOsDados(String tipo, HttpServletResponse response) {
		Object dados = null;
		
		if(tipo.equals("json")) {
			logger.info("Tipo json suportado.");
			dados = wsClienteService.getTodosOsDados() ;
		} else {
			dados = gerarDados(tipo, response, dados);
		}
		
		return dados;
	}

	public Object gerarDados(String tipo, HttpServletResponse response, Object dados) {
		Optional<Retorno> optionalRetorno = retornoFactory.getRetorno(tipo);
		if(optionalRetorno.isPresent()) {
			logger.info("Tipo " + tipo + " suportado.");
			dados = transformarDados(response, optionalRetorno);
		}
		return dados;
	}

	public Object transformarDados(HttpServletResponse response, Optional<Retorno> optionalRetorno) {
		return optionalRetorno.get().transformarDados(wsClienteService.getTodosOsDados(), response);
	}
	
}
