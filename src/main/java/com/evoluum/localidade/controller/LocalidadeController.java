package com.evoluum.localidade.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evoluum.localidade.dto.MunicipioDTO;
import com.evoluum.localidade.service.LocalidadeService;

@RestController
@RequestMapping(value = "/api/evoluum/localidade")
public class LocalidadeController {
	
	private Logger logger = LoggerFactory.getLogger(LocalidadeController.class);
	
	@Autowired
	private LocalidadeService localidadeService;
	
	@GetMapping("/todos/{retorno}")
	public ResponseEntity<Object> getTodosOsDados(@PathVariable String retorno, HttpServletResponse response){
		logger.info("Iniciando requisição para todos os dados.");
		localidadeService.getTodosOsDados(retorno, response);
		return ResponseEntity.status(response.getContentType() == null? HttpStatus.NOT_FOUND : HttpStatus.OK).body(null);
	}
	
	@GetMapping("/municipio/{nomeCidade}")
	public ResponseEntity<List<MunicipioDTO>> getIdMunicipio(@PathVariable String nomeCidade){
		logger.info("Iniciando requisição para nome de cidade.");
		List<MunicipioDTO> listaMunicipioDTO = localidadeService.getIdMunicipio(nomeCidade.trim().toLowerCase());
		return ResponseEntity.status(HttpStatus.OK).body(listaMunicipioDTO);
	}
}
