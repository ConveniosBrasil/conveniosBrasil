package br.com.fapen.conveniosBrasil.repositories;

import org.springframework.data.domain.PageRequest;

public class Paginacao {
	private static final Integer registro_pagina = 10;
	
	public static PageRequest getPaginacao(Integer pagina) {
		if(pagina > 0) {
			return PageRequest.of(pagina -1, registro_pagina);
		} else {
			return PageRequest.of(0, registro_pagina);
		}
	}
}
