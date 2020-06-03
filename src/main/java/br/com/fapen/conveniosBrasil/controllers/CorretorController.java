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

import br.com.fapen.conveniosBrasil.models.Corretor;
import br.com.fapen.conveniosBrasil.models.Usuario;
import br.com.fapen.conveniosBrasil.repositories.CorretorRepository;
import br.com.fapen.conveniosBrasil.repositories.Paginacao;
import br.com.fapen.conveniosBrasil.repositories.UsuarioRepository;
import br.com.fapen.conveniosBrasil.validations.CorretorValidator;

@Controller
@RequestMapping("/corretor")
public class CorretorController {
	@Autowired
	private CorretorRepository repCorretor;
	
	@Autowired
	private UsuarioRepository repUsuario;

	@Autowired
	private CorretorValidator validadorCorretor;

	@InitBinder("corretor")
	protected void init(WebDataBinder binder) {
		binder.setValidator(validadorCorretor);
	}

	@RequestMapping(value = "/novo", method = RequestMethod.GET, name = "novoCorretorUrl")
	public String formulario(Corretor corretor) {
		return "menuadmin/corretor/cadastro";
	}

	@RequestMapping(method = RequestMethod.POST, name = "salvarCorretorUrl")
	public String salvarNoBanco(@Valid Corretor corretor, BindingResult resultadoValidacao,
			RedirectAttributes atributos) {

		if (resultadoValidacao.hasErrors()) {
			return formulario(corretor);
		}

		repCorretor.save(corretor);
		atributos.addFlashAttribute("mensagemStatus", "Corretor salvo com sucesso");
		return "redirect:/corretor";
	}

	@RequestMapping(method = RequestMethod.GET, name = "listarCorretorUrl")
	public ModelAndView listar(@RequestParam(defaultValue = "1") Integer pagina,
			@RequestParam(defaultValue = "") String busca, Principal usuarioPrincipal) {
		Page<Corretor> listaDeCorretorCadastrados;

		Usuario perfilUsuario = repUsuario.findByUsername(usuarioPrincipal.getName());
		if (busca.equals("") && perfilUsuario.temPerfil("ROLE_ADMIN")) {
			listaDeCorretorCadastrados = repCorretor.findByOrderByIdAsc(Paginacao.getPaginacao(pagina));
		} else if (busca.equals("") && !perfilUsuario.temPerfil("ROLE_ADMIN")) {
			listaDeCorretorCadastrados = repCorretor.findByVisivelOrderByIdAsc("S", Paginacao.getPaginacao(pagina));
		}else if (!busca.equals("") && perfilUsuario.temPerfil("ROLE_ADMIN")) {
			listaDeCorretorCadastrados = repCorretor.findByNomeContainingIgnoreCase(busca, Paginacao.getPaginacao(pagina));
		} else
			listaDeCorretorCadastrados = repCorretor.findByNomeContainingIgnoreCaseAndVisivel(busca, "S",
					Paginacao.getPaginacao(pagina));

		ModelAndView mav = new ModelAndView("menuadmin/corretor/lista");
		mav.addObject("listaPaginada", listaDeCorretorCadastrados);
		mav.addObject("busca", busca);

		return mav;
	}

	@RequestMapping(value = "/{id}/editar", method = RequestMethod.GET, name = "alterarCorretorUrl")
	public String alterar(@PathVariable Long id, Model model) {
		Corretor corretorQueSeraAlterado = repCorretor.findById(id).get();
		
		model.addAttribute(corretorQueSeraAlterado);
		return formulario(corretorQueSeraAlterado);
	}

	@RequestMapping(value = "/{id}/visualizar", method = RequestMethod.GET, name = "visualizarCorretorUrl")
	public ModelAndView visualizar(@PathVariable Long id) {
		Corretor corretorQueSeraVisualizado = repCorretor.findById(id).get();

		ModelAndView mav = new ModelAndView("menuadmin/corretor/visualizar");
		mav.addObject("corretor", corretorQueSeraVisualizado);

		return mav;
	}
	
	@RequestMapping(value = "/{id}/ativar", method = RequestMethod.POST, name = "ativarCorretorUrl")
	public String ativar(@PathVariable Long id, RedirectAttributes atributos) {
		Corretor corretorQueSeraAlterado = repCorretor.findById(id).get();
	
		corretorQueSeraAlterado.setVisivel("S");
		
		repCorretor.save(corretorQueSeraAlterado);		 
		atributos.addFlashAttribute("mensagemStatus", "Corretor ativado com sucesso");
		return "redirect:/corretor";
	}

	@RequestMapping(value = "/{id}/inativar", method = RequestMethod.POST, name = "inativarCorretorUrl")
	public String inativar(@PathVariable Long id, RedirectAttributes atributos) {
		Corretor corretorQueSeraAlterado = repCorretor.findById(id).get();
	
		corretorQueSeraAlterado.setVisivel("N");
		
		repCorretor.save(corretorQueSeraAlterado);		 
		atributos.addFlashAttribute("mensagemStatus", "Corretor inativado com sucesso");
		return "redirect:/corretor";
	}
}
