package br.com.fapen.conveniosBrasil.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.fapen.conveniosBrasil.models.Usuario;
import br.com.fapen.conveniosBrasil.repositories.UsuarioRepository;

@Component
public class EsqueciSenhaValidator implements Validator {

	@Autowired
	private UsuarioRepository repUsuario;

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "campo.obrigatorio");

		Usuario emailQueSeraValidado = (Usuario) target;

		Usuario emailValidado = repUsuario.findByUsername(emailQueSeraValidado.getUsername());

		if (emailQueSeraValidado.getUsername() != "") {
			if (emailValidado == null) 
				errors.rejectValue("username", "email.inexistente");
			else if (emailValidado != null && !emailQueSeraValidado.getUsername().contains("@") || !emailQueSeraValidado.getUsername().contains(".com"))
				errors.rejectValue("username", "email.invalido");
			else if (emailValidado != null && !emailValidado.getId().equals(emailQueSeraValidado.getId())
					&& emailValidado.getVisivel().equals("N"))
				errors.rejectValue("username", "email.inativo"); 
		}
	}
}
