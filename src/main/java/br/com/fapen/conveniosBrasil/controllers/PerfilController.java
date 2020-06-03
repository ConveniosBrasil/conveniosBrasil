package br.com.fapen.conveniosBrasil.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fapen.conveniosBrasil.models.Perfil;
import br.com.fapen.conveniosBrasil.models.Usuario;
import br.com.fapen.conveniosBrasil.repositories.Paginacao;
import br.com.fapen.conveniosBrasil.repositories.PerfilRepository;
import br.com.fapen.conveniosBrasil.repositories.UsuarioRepository;
import br.com.fapen.conveniosBrasil.validations.PerfilValidator;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
	@Autowired
	private PerfilRepository repPerfil;

	@Autowired
	private UsuarioRepository repUsuario;
	
	@Autowired
	private PerfilValidator validadorPerfil;

	@InitBinder("perfil")
	protected void init(WebDataBinder binder) {
		binder.setValidator(validadorPerfil);
	}

	@RequestMapping(value = "/novo", method = RequestMethod.GET, name = "novoPerfilUrl")
	public String formulario(Perfil perfil) {
		return "menuadmin/perfil/cadastro";
	}

	@RequestMapping(method = RequestMethod.POST, name = "salvarPerfilUrl")
	public String salvarNoBanco(@Valid Perfil perfil, BindingResult resultadoValidacao,
			RedirectAttributes atributos) {

		if (resultadoValidacao.hasErrors()) {
			return formulario(perfil);
		}

		repPerfil.save(perfil);
		atributos.addFlashAttribute("mensagemStatus", "Perfil salvo com sucesso");
		return "redirect:/perfil";
	}

	@RequestMapping(method = RequestMethod.GET, name = "listarPerfilUrl")
	public ModelAndView listar(@RequestParam(defaultValue = "1") Integer pagina,
			@RequestParam(defaultValue = "") String busca, Principal usuarioPrincipal) {
		Page<Perfil> listaDePerfilCadastrados;

		Usuario perfilUsuario = repUsuario.findByUsername(usuarioPrincipal.getName());
		if (busca.equals("") && perfilUsuario.temPerfil("ROLE_ADMIN")) {
			listaDePerfilCadastrados = repPerfil.findByOrderByIdAsc(Paginacao.getPaginacao(pagina));
		} else if (busca.equals("") && !perfilUsuario.temPerfil("ROLE_ADMIN")) {
			listaDePerfilCadastrados = repPerfil.findByVisivelOrderByIdAsc("S", Paginacao.getPaginacao(pagina));
		}else if (!busca.equals("") && perfilUsuario.temPerfil("ROLE_ADMIN")) {
			listaDePerfilCadastrados = repPerfil.findByAuthorityContainingIgnoreCase(busca, Paginacao.getPaginacao(pagina));
		} else
			listaDePerfilCadastrados = repPerfil.findByAuthorityContainingIgnoreCaseAndVisivel(busca, "S",
					Paginacao.getPaginacao(pagina));

		ModelAndView mav = new ModelAndView("menuadmin/perfil/lista");
		mav.addObject("listaPaginada", listaDePerfilCadastrados);
		mav.addObject("busca", busca);

		return mav;
	}

	@RequestMapping(value = "/{id}/editar", method = RequestMethod.GET, name = "alterarPerfilUrl")
	public String alterar(@PathVariable Long id, Model model) {
		Perfil perfilQueSeraAlterado = repPerfil.findById(id).get();
		
		model.addAttribute(perfilQueSeraAlterado);
		return formulario(perfilQueSeraAlterado);
	}

	@RequestMapping(value = "/{id}/visualizar", method = RequestMethod.GET, name = "visualizarPerfilUrl")
	public ModelAndView visualizar(@PathVariable Long id) {
		Perfil perfilQueSeraVisualizado = repPerfil.findById(id).get();

		ModelAndView mav = new ModelAndView("menuadmin/perfil/visualizar");
		mav.addObject("perfil", perfilQueSeraVisualizado);

		return mav;
	}
	
	@RequestMapping(value = "/{id}/ativar", method = RequestMethod.POST, name = "ativarPerfilUrl")
	public String ativar(@PathVariable Long id, RedirectAttributes atributos) {
		Perfil perfilQueSeraAlterado = repPerfil.findById(id).get();
	
		perfilQueSeraAlterado.setVisivel("S");
		
		repPerfil.save(perfilQueSeraAlterado);		 
		atributos.addFlashAttribute("mensagemStatus", "Perfil ativado com sucesso");
		return "redirect:/perfil";
	}

	@RequestMapping(value = "/{id}/inativar", method = RequestMethod.POST, name = "inativarPerfilUrl")
	public String inativar(@PathVariable Long id, RedirectAttributes atributos) {
		Perfil perfilQueSeraAlterado = repPerfil.findById(id).get();
		
		if (perfilQueSeraAlterado.getAuthority().trim().equalsIgnoreCase("ROLE_ADMIN")) {
			atributos.addFlashAttribute("mensagemStatus", "Permissão de administrador não pode ser apagado");
			return "redirect:/perfil";
		}
	
		perfilQueSeraAlterado.setVisivel("N");
		
		repPerfil.save(perfilQueSeraAlterado);		 
		atributos.addFlashAttribute("mensagemStatus", "Perfil inativado com sucesso");
		return "redirect:/perfil";
	}
}
