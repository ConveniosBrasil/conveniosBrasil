package br.com.fapen.conveniosBrasil.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.fapen.conveniosBrasil.models.Perfil;
import br.com.fapen.conveniosBrasil.repositories.PerfilRepository;

@Component
public class PerfilValidator implements Validator {

	@Autowired
	private PerfilRepository repPerfil;

	@Override
	public boolean supports(Class<?> clazz) {
		return Perfil.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "authority", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descricao", "campo.obrigatorio");

		Perfil authorityQueSeraValidado = (Perfil) target;

		if (authorityQueSeraValidado.getAuthority() != "") {
			if (!authorityQueSeraValidado.getAuthority().toUpperCase().contains("ROLE_")) {
				errors.rejectValue("authority", "permissao.incorreta");
			}
		}

		Perfil perfilAuthorityIgual = repPerfil.findByAuthority(authorityQueSeraValidado.getAuthority());

		if (perfilAuthorityIgual != null && !perfilAuthorityIgual.getId().equals(authorityQueSeraValidado.getId())
				&& perfilAuthorityIgual.getVisivel().equals("S")) {
			errors.rejectValue("authority", "campo.duplicado");
		} else if (perfilAuthorityIgual != null && !perfilAuthorityIgual.getId().equals(authorityQueSeraValidado.getId())
				&& perfilAuthorityIgual.getVisivel().equals("N")) {
			errors.rejectValue("authority", "cadastro.inativo");
		}
	}
}
