package br.com.fapen.conveniosBrasil.validations;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.fapen.conveniosBrasil.models.Corretor;
import br.com.fapen.conveniosBrasil.repositories.CorretorRepository;

@Component
public class CorretorValidator implements Validator {

	private CPFValidator validadorCpf = new CPFValidator();

	@Autowired
	private CorretorRepository repCorretor;

	@Override
	public boolean supports(Class<?> clazz) {
		return Corretor.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cpf", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rg", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sexo", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefone", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataNascimento", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cep", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rua", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numero", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bairro", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cidade", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "estado", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "campo.obrigatorio");

		Corretor corretorQueSeraValidado = (Corretor) target;

		List<ValidationMessage> validationMessages = validadorCpf.invalidMessagesFor(corretorQueSeraValidado.getCpf());

		Corretor corretorCpfIgual = repCorretor.findByCpf(corretorQueSeraValidado.getCpf());
		Corretor corretorEmailIgual = repCorretor.findByEmail(corretorQueSeraValidado.getEmail());

		if (corretorQueSeraValidado.getCpf() != "") {
			if (!validationMessages.isEmpty()) {
				errors.rejectValue("cpf", "cpf.invalido");
			} else if (corretorCpfIgual != null && !corretorCpfIgual.getId().equals(corretorQueSeraValidado.getId())
					&& corretorCpfIgual.getVisivel().equals("S")) {
				errors.rejectValue("cpf", "campo.duplicado");
			} else if (corretorCpfIgual != null && !corretorCpfIgual.getId().equals(corretorQueSeraValidado.getId())
					&& corretorCpfIgual.getVisivel().equals("N")) {
				errors.rejectValue("cpf", "cadastro.inativo");
			}
		}

		if (corretorQueSeraValidado.getEmail() != "") {
			if (!corretorQueSeraValidado.getEmail().contains("@") || !corretorQueSeraValidado.getEmail().contains(".com")) {
				errors.rejectValue("email", "email.invalido");
			} else if (corretorEmailIgual != null && !corretorEmailIgual.getId().equals(corretorQueSeraValidado.getId())
					&& corretorCpfIgual.getVisivel().equals("S")) {
				errors.rejectValue("email", "campo.duplicado");
			} else if (corretorEmailIgual != null && !corretorEmailIgual.getId().equals(corretorQueSeraValidado.getId())
					&& corretorCpfIgual.getVisivel().equals("N")) {
				errors.rejectValue("email", "cadastro.inativo");
			}
		}
		
		LocalDate dataLimite = LocalDate.now().minusYears(18);
		if (corretorQueSeraValidado.getDataNascimento() != null) {
			if (corretorQueSeraValidado.getDataNascimento().isAfter(dataLimite)) {
				errors.rejectValue("dataNascimento", "menor.idade");
			}
		}
	}
}
