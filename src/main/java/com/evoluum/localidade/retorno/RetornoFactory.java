package com.evoluum.localidade.retorno;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class RetornoFactory {
	
	public Optional<Retorno> getRetorno(String tipo) {		
        if (tipo.equals("csv"))
            return Optional.ofNullable(new RetornoCSV());  
        if (tipo.equals("json"))
            return Optional.ofNullable(new RetornoJSON()); 
        return Optional.ofNullable(null);
    }
}
