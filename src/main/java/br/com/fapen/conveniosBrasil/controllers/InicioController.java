package br.com.fapen.conveniosBrasil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InicioController {
	
	@RequestMapping(value ="/home", method = RequestMethod.GET, name = "homeUrl")
	public String home() {
		return "home";
	}
	
	@RequestMapping(value ="/plano", method = RequestMethod.GET, name = "planoUrl")
	public String plano() {
		return "plano";
	}
	
	@RequestMapping(value ="/sobre", method = RequestMethod.GET, name = "sobreUrl")
	public String sobre() {
		return "sobre";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET, name = "loginUrl")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/politicaprivacidade", method = RequestMethod.GET, name = "politicaPrivacidadeUrl")
	public String politicaPrivacidade() {
		return "politicaprivacidade";
	}
	
	@RequestMapping(value = "/esquecisenha/emailenviado", method = RequestMethod.GET, name = "emailEnviadoUrl")
	public String emailEnviado() {
		return "recuperarsenha/emailEnviado";
	}
	
	@RequestMapping(value = "/esquecisenha/erro", method = RequestMethod.GET, name = "erroTokenUrl")
	public String erroToken() {
		return "recuperarsenha/erroToken";
	}
}
