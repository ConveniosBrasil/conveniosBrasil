package br.com.fapen.conveniosBrasil.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fapen.conveniosBrasil.forms.ContatoForm;
import br.com.fapen.conveniosBrasil.services.EmailService;

@Controller
@RequestMapping("/contato")
public class EmailContatoController {

	@Autowired
	private EmailService servicoEmail;

	@RequestMapping(method = RequestMethod.GET, name = "contatoUrl")
	public String formulario(ContatoForm contatoForm) {
		return "contato";
	}
	
	@RequestMapping(method = RequestMethod.POST, name = "enviarEmailContatoUrl")
	public String verificarEmail(ContatoForm contatoForm, RedirectAttributes atributos) {

		servicoEmail.EnviarEmailContatoHTML(contatoForm.getNome(),contatoForm.getEmail(), contatoForm.getTelefone(), contatoForm.getMensagem());
		atributos.addFlashAttribute("mensagemStatus", "Mensagem enviada com sucesso");
		return "redirect:/contato";
	}
}
