package br.com.fapen.conveniosBrasil.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fapen.conveniosBrasil.forms.SenhaForm;
import br.com.fapen.conveniosBrasil.models.Usuario;
import br.com.fapen.conveniosBrasil.repositories.UsuarioRepository;
import br.com.fapen.conveniosBrasil.services.UsuarioService;
import br.com.fapen.conveniosBrasil.validations.CadastrarSenhaValidator;

@Controller
@RequestMapping("/esquecisenha")
public class CadastrarSenhaController {

	@Autowired
	private CadastrarSenhaValidator validadorCadastrarSenha;
	
	@Autowired
	private UsuarioService servicoUsuario;
	
	@Autowired
	private UsuarioRepository repUsuario;

	@InitBinder("senhaForm")
	protected void init(WebDataBinder binder) {
		binder.setValidator(validadorCadastrarSenha);
	}
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET, name = "novaSenhaUrl")
	public String formulario(SenhaForm senhaForm) {
		return "recuperarsenha/cadastro"; 
	}

	@RequestMapping(value = "/cadastro", method = RequestMethod.POST,name = "novaSenha")
	public String salvarSenha(@Valid SenhaForm senhaForm, BindingResult resultadoValidacao, RedirectAttributes atributos) {
		String tokenForm = senhaForm.getUsuario().getToken();
		Usuario token = repUsuario.findByToken(tokenForm);		
		
		if (token == null) {
			return "redirect:/esquecisenha/erro";
		}
		
		if (resultadoValidacao.hasErrors()) { 
			return formulario(senhaForm);
		}	
		
		servicoUsuario.salvarSenhaNova(senhaForm, token.getId());
		return "/recuperarsenha/senhaSalva";
	}
}
