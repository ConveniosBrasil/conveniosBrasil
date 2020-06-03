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
import br.com.fapen.conveniosBrasil.forms.UsuarioForm;
import br.com.fapen.conveniosBrasil.models.Usuario;
import br.com.fapen.conveniosBrasil.repositories.UsuarioRepository;

@Component
public class UsuarioValidator implements Validator {

	private CPFValidator validadorCpf = new CPFValidator();

	@Autowired
	private UsuarioRepository repUsuario;

	@Override
	public boolean supports(Class<?> clazz) {
		return UsuarioForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.nome", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.cpf", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.rg", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.sexo", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.telefone", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.data_nascimento", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.cep", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.rua", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.numero", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.bairro", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.cidade", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.estado", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.username", "campo.obrigatorio");

		UsuarioForm usuarioQueSeraValidado = (UsuarioForm) target;

		List<ValidationMessage> validationMessages = validadorCpf.invalidMessagesFor(usuarioQueSeraValidado.getUsuario().getCpf());

		Usuario usuarioCpfIgual = repUsuario.findByCpf(usuarioQueSeraValidado.getUsuario().getCpf());
		Usuario usuarioEmailIgual = repUsuario.findByUsername(usuarioQueSeraValidado.getUsuario().getUsername());

		if (usuarioQueSeraValidado.getUsuario().getCpf() != "") {
			if (!validationMessages.isEmpty()) {
				errors.rejectValue("usuario.cpf", "cpf.invalido");
			} else if (usuarioCpfIgual != null && !usuarioCpfIgual.getId().equals(usuarioQueSeraValidado.getUsuario().getId())
					&& usuarioCpfIgual.getVisivel().equals("S")) {
				errors.rejectValue("usuario.cpf", "campo.duplicado");
			} else if (usuarioCpfIgual != null && !usuarioCpfIgual.getId().equals(usuarioQueSeraValidado.getUsuario().getId())
					&& usuarioCpfIgual.getVisivel().equals("N")) {
				errors.rejectValue("usuario.cpf", "cadastro.inativo");
			}
		}

		if (usuarioQueSeraValidado.getUsuario().getUsername() != "") {
			if (!usuarioQueSeraValidado.getUsuario().getUsername().contains("@") || !usuarioQueSeraValidado.getUsuario().getUsername().contains(".com")) {
				errors.rejectValue("usuario.username", "email.invalido");
			} else if (usuarioEmailIgual != null && !usuarioEmailIgual.getId().equals(usuarioQueSeraValidado.getUsuario().getId())
					&& usuarioEmailIgual.getVisivel().equals("S")) {
				errors.rejectValue("usuario.username", "campo.duplicado");
			} else if (usuarioEmailIgual != null && !usuarioEmailIgual.getId().equals(usuarioQueSeraValidado.getUsuario().getId())
					&& usuarioEmailIgual.getVisivel().equals("N")) {
				errors.rejectValue("usuario.username", "cadastro.inativo");
			}
		}
		
		if (usuarioQueSeraValidado.isInclusao()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario.password", "campo.obrigatorio");
		}
		
		if (usuarioQueSeraValidado.getListaPerfil().isEmpty()) {
			errors.rejectValue("listaPerfil", "perfil.obrigatorio");
		}

		LocalDate dataLimite = LocalDate.now().minusYears(18);
		if (usuarioQueSeraValidado.getUsuario().getData_nascimento() != null) {
			if (usuarioQueSeraValidado.getUsuario().getData_nascimento().isAfter(dataLimite)) {
				errors.rejectValue("usuario.data_nascimento", "menor.idade");
			}
		}
	}
}
