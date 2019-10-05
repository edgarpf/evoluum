package com.evoluum.localidade.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evoluum.localidade.dto.MunicipioDTO;
import com.evoluum.localidade.retorno.Retorno;
import com.evoluum.localidade.retorno.RetornoFactory;

@Service
public class LocalidadeService {	
	
	private Logger logger = LoggerFactory.getLogger(LocalidadeService.class);
	
	@Autowired
	private RetornoFactory retornoFactory;
	
	@Autowired
	private WSClienteService wsClienteService;
	
	public void getTodosOsDados(String tipo, HttpServletResponse response) {
		Optional<Retorno> optionalRetorno = retornoFactory.getRetorno(tipo);
		if(optionalRetorno.isPresent()) {
			logger.info("Tipo " + tipo + " suportado.");
			optionalRetorno.get().transformarDados(wsClienteService.getTodosOsDados(), response);		
		}
	}

	public List<MunicipioDTO> getIdMunicipio(String nomeCidade) {
		return wsClienteService.getIdMunicipio(nomeCidade);
	}
}
