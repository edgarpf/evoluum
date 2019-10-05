package com.evoluum.localidade.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.evoluum.localidade.dto.EstadoDTO;
import com.evoluum.localidade.dto.LocalDTO;
import com.evoluum.localidade.dto.MunicipioDTO;

@Service
public class WSClienteService {

	private Logger logger = LoggerFactory.getLogger(WSClienteService.class);

	@Value("${endpoint.estados}")
	private String estadosEndpoint;

	@Value("${endpoint.municipios}")
	private String municipiosEndpoint;

	@Autowired
	private RestTemplate restTemplate;

	public List<LocalDTO> getTodosOsDados() {
		List<LocalDTO> listaLocais = new ArrayList<>();

		logger.info("Lendo estados.");
		List<EstadoDTO> listaEstados = Arrays.asList(restTemplate.getForEntity(estadosEndpoint, EstadoDTO[].class).getBody());
		logger.info("Estados lidos com sucesso.");

		logger.info("Lendo municipios de cada estado.");
		listaEstados.forEach(estadoDTO -> {
			List<MunicipioDTO> listaMunicipios = Arrays.asList(restTemplate.getForEntity(String.format(municipiosEndpoint, estadoDTO.getId()), MunicipioDTO[].class).getBody());
			listaMunicipios.forEach(municipioDTO -> listaLocais.add(new LocalDTO(estadoDTO, municipioDTO)));
		});
		logger.info("Municipios lidos com sucesso.");

		return listaLocais;
	}
}
