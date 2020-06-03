package br.com.fapen.conveniosBrasil.validations;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.fapen.conveniosBrasil.forms.PedidoCompraForm;
import br.com.fapen.conveniosBrasil.models.ItemPedidoCompra;

@Component
public class PedidoCompraFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PedidoCompraForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pedidoCompra.fornecedor", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pedidoCompra.dataEntrega", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pedidoCompra.condicaoPagamento", "campo.obrigatorio");

		PedidoCompraForm formulario = (PedidoCompraForm) target;
		
		if (formulario.getItemPedidoCompra().isEmpty())
			errors.rejectValue("itemPedidoCompra", "lista.vazia");
		
		for (int i = 0; i < formulario.getItemPedidoCompra().size(); i++) {
			ItemPedidoCompra itemPedido = formulario.getItemPedidoCompra().get(i);

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemPedidoCompra[" + i + "].produto", "campo.obrigatorio");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemPedidoCompra[" + i + "].quantidade", "campo.obrigatorio");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemPedidoCompra[" + i + "].precoUnitario", "campo.obrigatorio");
			
			if (itemPedido.getQuantidade() != null && itemPedido.getQuantidade().compareTo(0d) == 0)
				errors.rejectValue("itemPedidoCompra[" + i + "].quantidade", "campo.obrigatorio");
			
			if (itemPedido.getPrecoUnitario() != null && itemPedido.getPrecoUnitario().compareTo(BigDecimal.ZERO) == 0)
				errors.rejectValue("itemPedidoCompra[" + i + "].precoUnitario", "campo.obrigatorio");
		}
	}
}
