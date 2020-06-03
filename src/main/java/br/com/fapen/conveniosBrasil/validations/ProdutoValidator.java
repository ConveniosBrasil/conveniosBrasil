package br.com.fapen.conveniosBrasil.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.fapen.conveniosBrasil.models.Produto;
import br.com.fapen.conveniosBrasil.repositories.ProdutoRepository;

@Component
public class ProdutoValidator implements Validator {
	
	@Autowired
	private ProdutoRepository repProduto;

	@Override
	public boolean supports(Class<?> clazz) {
		return Produto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomeProduto", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descricao", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "preco", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "saldo", "campo.obrigatorio");

		Produto produtoQueSeraValidado = (Produto) target;
		Produto produtoIgual = repProduto.findByNomeProduto(produtoQueSeraValidado.getNomeProduto());

		if (produtoQueSeraValidado.getNomeProduto() != "") {
			if (produtoIgual != null && !produtoIgual.getId().equals(produtoQueSeraValidado.getId())
					&& produtoIgual.getVisivel().equals("S")) {
				errors.rejectValue("nomeProduto", "campo.duplicado");
			} else if (produtoIgual != null && !produtoIgual.getId().equals(produtoQueSeraValidado.getId())
					&& produtoIgual.getVisivel().equals("N")) {
				errors.rejectValue("nomeProduto", "cadastro.inativo");
			}
		}
		
		if (produtoQueSeraValidado.getSaldo() != null && produtoQueSeraValidado.getSaldo() < 1) {
			errors.rejectValue("saldo", "campo.negativo");			
		}
		
		if (produtoQueSeraValidado.getPreco() != null && produtoQueSeraValidado.getPreco() < 1) {
			errors.rejectValue("preco", "campo.negativo");			
		}
	}
}
