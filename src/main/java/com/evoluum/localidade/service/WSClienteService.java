package com.evoluum.localidade.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.evoluum.localidade.dto.Estado;
import com.evoluum.localidade.dto.LocalDTO;
import com.evoluum.localidade.dto.Municipio;
import com.evoluum.localidade.dto.MunicipioDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class WSClienteService {

	private Logger logger = LoggerFactory.getLogger(WSClienteService.class);

	@Value("${endpoint.estados}")
	private String estadosEndpoint;

	@Value("${endpoint.municipios}")
	private String municipiosEndpoint;

	@Value("${endpoint.todosMunicipios}")
	private String todosMunicipiosEndpoint;

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getTodosOsDadosVazio", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "15000") })
	public List<LocalDTO> getTodosOsDados() {
		List<LocalDTO> listaLocais = new ArrayList<>();

		logger.info("Lendo estados.");
		List<Estado> listaEstados = Arrays.asList(restTemplate.getForEntity(estadosEndpoint, Estado[].class).getBody());
		logger.info("Estados lidos com sucesso.");

		logger.info("Lendo municipios de cada estado.");
		listaEstados.forEach(estado -> {
			List<Municipio> listaMunicipios = Arrays.asList(restTemplate
					.getForEntity(String.format(municipiosEndpoint, estado.getId()), Municipio[].class).getBody());
			listaMunicipios.forEach(municipioDTO -> listaLocais.add(new LocalDTO(estado, municipioDTO)));
		});
		logger.info("Municipios lidos com sucesso.");

		return listaLocais;
	}

	public List<LocalDTO> getTodosOsDadosVazio() {
		return new ArrayList<>();
	}

	@Cacheable("idMunicipio")
	public List<MunicipioDTO> getIdMunicipio(String nomeCidade) {
		logger.info("Lendo municipios.");
		List<Municipio> listaMunicipios = Arrays
				.asList(restTemplate.getForEntity(todosMunicipiosEndpoint, Municipio[].class).getBody());
		logger.info("Municipios lidos com sucesso.");
		return listaMunicipios.stream().filter(municipio -> municipio.getNome().equalsIgnoreCase(nomeCidade))
				.map(municipio -> new MunicipioDTO(municipio.getId())).collect(Collectors.toList());
	}
}
