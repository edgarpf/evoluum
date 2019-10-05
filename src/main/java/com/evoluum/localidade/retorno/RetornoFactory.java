package com.evoluum.localidade.retorno;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class RetornoFactory {
	
	public Optional<Retorno> getRetorno(String tipo) {		
        if (tipo.equalsIgnoreCase("csv"))
            return Optional.ofNullable(new RetornoCSV());  
        
        return Optional.ofNullable(null);
    }
}
