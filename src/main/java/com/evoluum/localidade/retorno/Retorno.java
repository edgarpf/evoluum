package com.evoluum.localidade.retorno;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.evoluum.localidade.dto.LocalDTO;

public interface Retorno {
	public void transformarDados(List<LocalDTO> list, HttpServletResponse response);
}
