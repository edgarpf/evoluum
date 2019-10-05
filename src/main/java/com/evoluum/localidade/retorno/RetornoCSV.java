package com.evoluum.localidade.retorno;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.evoluum.localidade.dto.LocalDTO;

public class RetornoCSV implements Retorno {

	private Logger logger = LoggerFactory.getLogger(RetornoCSV.class);
	private static final String FILE_NAME = "dados.csv";
	
	@Override
	public Object transformarDados(List<LocalDTO> list, HttpServletResponse response) {
		try {
			logger.info("Gerando arquivo csv.");
			gerarAquivo(response, gerarTexto(list));
			logger.info("Arquivo csv gerado com sucesso.");
		} catch (IOException e) {
			logger.info("Falha na geração do arquivo csv.");
			logger.error(e.getMessage());
		}

		return null;
	}

	public void gerarAquivo(HttpServletResponse response, StringBuilder texto) throws IOException {
		byte[] csv = texto.toString().getBytes(StandardCharsets.UTF_8);
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=" + FILE_NAME);
		response.setContentLength(csv.length);
		response.getOutputStream().write(csv);;
	}

	public StringBuilder gerarTexto(List<LocalDTO> list) {
		StringBuilder texto = new StringBuilder();
		texto.append("idEstado,siglaEstado,regiaoNome,nomeCidade,nomeMesorregiao,nomeFormatado\n");
		list.forEach(localDTO -> texto.append(localDTO.getIdEstado() + "," + localDTO.getSiglaEstado() + ","
				+ localDTO.getRegiaoNome() + "," + localDTO.getNomeCidade() + "," + localDTO.getNomeMesorregiao() + ","
				+ localDTO.getNomeFormatado() + "\n"));
		return texto;
	}
}
