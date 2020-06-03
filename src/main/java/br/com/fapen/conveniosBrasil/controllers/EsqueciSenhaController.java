package br.com.fapen.conveniosBrasil.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fapen.conveniosBrasil.models.Usuario;
import br.com.fapen.conveniosBrasil.repositories.UsuarioRepository;
import br.com.fapen.conveniosBrasil.services.EmailService;
import br.com.fapen.conveniosBrasil.validations.EsqueciSenhaValidator;

@Controller
@RequestMapping("/esquecisenha")
public class EsqueciSenhaController {

	@Autowired
	private EmailService servicoEmail;
	
	@Autowired
	private UsuarioRepository repUsuario;

	@Autowired
	private EsqueciSenhaValidator validadorEsqueciSenha;

	@InitBinder("usuario")
	protected void init(WebDataBinder binder) {
		binder.setValidator(validadorEsqueciSenha);
	}

	@RequestMapping(value = "/email", method = RequestMethod.GET, name = "esqueciSenhaUrl")
	public String formulario(Usuario usuario) {
		return "recuperarsenha/enviarEmail";
	}
	
	@RequestMapping(method = RequestMethod.POST, name = "verificarEmailUrl")
	public String verificarEmail(@Valid Usuario usuario, BindingResult resultadoValidacao, RedirectAttributes atributos,
		HttpServletRequest request) {

		if (resultadoValidacao.hasErrors()) {
			return formulario(usuario);
		}
		
		Usuario pesquisarUsuario = repUsuario.findByUsername(usuario.getUsername());
		

		servicoEmail.EnviarEmailRecSenhaHTML(request, pesquisarUsuario);
		return "redirect:/esquecisenha/emailenviado";
	}
}
