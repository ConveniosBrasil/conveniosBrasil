package br.com.fapen.conveniosBrasil.validations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CNPJValidator;
import br.com.fapen.conveniosBrasil.models.Fornecedor;
import br.com.fapen.conveniosBrasil.repositories.FornecedorRepository;

@Component
public class FornecedorValidator implements Validator {

	private CNPJValidator validadorCnpj = new CNPJValidator();

	@Autowired
	private FornecedorRepository repFornecedor;

	@Override
	public boolean supports(Class<?> clazz) {
		return Fornecedor.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "razaoSocial", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomeFantasia", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomeResponsavel", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cnpj", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefone", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cep", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rua", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numero", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bairro", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cidade", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "estado", "campo.obrigatorio");

		Fornecedor fornecedorQueSeraValidado = (Fornecedor) target;
		List<ValidationMessage> validationMessages = validadorCnpj
				.invalidMessagesFor(fornecedorQueSeraValidado.getCnpj());

		Fornecedor fornecedorCnpjIgual = repFornecedor.findByCnpj(fornecedorQueSeraValidado.getCnpj());
		Fornecedor fornecedorEmailIgual = repFornecedor.findByEmail(fornecedorQueSeraValidado.getEmail());

		if (fornecedorQueSeraValidado.getCnpj() != "") {
			if (!validationMessages.isEmpty()) {
				errors.rejectValue("cnpj", "cnpj.invalido");
			} else if (fornecedorCnpjIgual != null
					&& !fornecedorCnpjIgual.getId().equals(fornecedorQueSeraValidado.getId())
					&& fornecedorCnpjIgual.getVisivel().equals("S")) {
				errors.rejectValue("cnpj", "campo.duplicado");
			} else if (fornecedorCnpjIgual != null
					&& !fornecedorCnpjIgual.getId().equals(fornecedorQueSeraValidado.getId())
					&& fornecedorCnpjIgual.getVisivel().equals("N")) {
				errors.rejectValue("cnpj", "cadastro.inativo");
			}
		}

		if (fornecedorQueSeraValidado.getEmail() != "") {
			if (!fornecedorQueSeraValidado.getEmail().contains("@")
					|| !fornecedorQueSeraValidado.getEmail().contains(".com")) {
				errors.rejectValue("email", "email.invalido");
			} else if (fornecedorEmailIgual != null
					&& !fornecedorEmailIgual.getId().equals(fornecedorQueSeraValidado.getId())
					&& fornecedorEmailIgual.getVisivel().equals("S")) {
				errors.rejectValue("email", "campo.duplicado");
			} else if (fornecedorEmailIgual != null
					&& !fornecedorEmailIgual.getId().equals(fornecedorQueSeraValidado.getId())
					&& fornecedorEmailIgual.getVisivel().equals("N")) {
				errors.rejectValue("email", "cadastro.inativo");
			}
		}
	}
}
