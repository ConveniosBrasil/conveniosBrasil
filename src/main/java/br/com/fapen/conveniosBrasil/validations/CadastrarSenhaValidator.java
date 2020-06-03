package br.com.fapen.conveniosBrasil.validations;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.fapen.conveniosBrasil.forms.SenhaForm;

@Component
public class CadastrarSenhaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SenhaForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.password", "campo.obrigatorio");

		SenhaForm senhaQueSeraValidada = (SenhaForm) target;
		if(senhaQueSeraValidada.getUsuario().getPassword() != "" || senhaQueSeraValidada.getRepetirSenha() != "") {
			if(!senhaQueSeraValidada.getUsuario().getPassword().equals(senhaQueSeraValidada.getRepetirSenha())) 
				errors.rejectValue("repetirSenha", "senha.diferente");
		} else if(senhaQueSeraValidada.getRepetirSenha() == "")
			errors.rejectValue("repetirSenha", "campo.obrigatorio");
	}
}
