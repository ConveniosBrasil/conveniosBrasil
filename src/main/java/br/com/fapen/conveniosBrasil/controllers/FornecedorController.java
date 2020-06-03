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

import br.com.fapen.conveniosBrasil.models.Fornecedor;
import br.com.fapen.conveniosBrasil.models.Usuario;
import br.com.fapen.conveniosBrasil.repositories.FornecedorRepository;
import br.com.fapen.conveniosBrasil.repositories.Paginacao;
import br.com.fapen.conveniosBrasil.repositories.UsuarioRepository;
import br.com.fapen.conveniosBrasil.validations.FornecedorValidator;

@Controller
@RequestMapping("/fornecedor")
public class FornecedorController {
	@Autowired
	private FornecedorRepository repFornecedor;
	
	@Autowired
	private UsuarioRepository repUsuario;

	@Autowired
	private FornecedorValidator validadorFornecedor;

	@InitBinder("fornecedor")
	protected void init(WebDataBinder binder) {
		binder.setValidator(validadorFornecedor);
	}

	@RequestMapping(value = "/novo", method = RequestMethod.GET, name = "novoFornecedorUrl")
	public String formulario(Fornecedor fornecedor) {
		return "menuadmin/fornecedor/cadastro";
	}

	@RequestMapping(method = RequestMethod.POST, name = "salvarFornecedorUrl")
	public String salvarNoBanco(@Valid Fornecedor fornecedor, BindingResult resultadoValidacao,
			RedirectAttributes atributos) {

		if (resultadoValidacao.hasErrors()) {
			return formulario(fornecedor);
		}

		repFornecedor.save(fornecedor);
		atributos.addFlashAttribute("mensagemStatus", "Fornecedor salvo com sucesso");
		return "redirect:/fornecedor";
	}

	@RequestMapping(method = RequestMethod.GET, name = "listarFornecedorUrl")
	public ModelAndView listar(@RequestParam(defaultValue = "1") Integer pagina,
			@RequestParam(defaultValue = "") String busca, Principal usuarioPrincipal) {
		Page<Fornecedor> listaDeFornecedorCadastrados;

		Usuario perfilUsuario = repUsuario.findByUsername(usuarioPrincipal.getName());
		if (busca.equals("") && perfilUsuario.temPerfil("ROLE_ADMIN")) {
			listaDeFornecedorCadastrados = repFornecedor.findByOrderByIdAsc(Paginacao.getPaginacao(pagina));
		} else if (busca.equals("") && !perfilUsuario.temPerfil("ROLE_ADMIN")) {
			listaDeFornecedorCadastrados = repFornecedor.findByVisivelOrderByIdAsc("S", Paginacao.getPaginacao(pagina));
		}else if (!busca.equals("") && perfilUsuario.temPerfil("ROLE_ADMIN")) {
			listaDeFornecedorCadastrados = repFornecedor.findByNomeFantasiaContainingIgnoreCase(busca, Paginacao.getPaginacao(pagina));
		} else
			listaDeFornecedorCadastrados = repFornecedor.findByNomeFantasiaContainingIgnoreCaseAndVisivel(busca, "S",
					Paginacao.getPaginacao(pagina));

		ModelAndView mav = new ModelAndView("menuadmin/fornecedor/lista");
		mav.addObject("listaPaginada", listaDeFornecedorCadastrados);
		mav.addObject("busca", busca);

		return mav;
	}

	@RequestMapping(value = "/{id}/editar", method = RequestMethod.GET, name = "alterarFornecedorUrl")
	public String alterar(@PathVariable Long id, Model model) {
		Fornecedor fornecedorQueSeraAlterado = repFornecedor.findById(id).get();
		
		model.addAttribute(fornecedorQueSeraAlterado);
		return formulario(fornecedorQueSeraAlterado);
	}

	@RequestMapping(value = "/{id}/visualizar", method = RequestMethod.GET, name = "visualizarFornecedorUrl")
	public ModelAndView visualizar(@PathVariable Long id) {
		Fornecedor fornecedorQueSeraVisualizado = repFornecedor.findById(id).get();

		ModelAndView mav = new ModelAndView("menuadmin/fornecedor/visualizar");
		mav.addObject("fornecedor", fornecedorQueSeraVisualizado);

		return mav;
	}
	
	@RequestMapping(value = "/{id}/ativar", method = RequestMethod.POST, name = "ativarFornecedorUrl")
	public String ativar(@PathVariable Long id, RedirectAttributes atributos) {
		Fornecedor fornecedorQueSeraAlterado = repFornecedor.findById(id).get();
	
		fornecedorQueSeraAlterado.setVisivel("S");
		
		repFornecedor.save(fornecedorQueSeraAlterado);		 
		atributos.addFlashAttribute("mensagemStatus", "Fornecedor ativado com sucesso");
		return "redirect:/fornecedor";
	}

	@RequestMapping(value = "/{id}/inativar", method = RequestMethod.POST, name = "inativarFornecedorUrl")
	public String inativar(@PathVariable Long id, RedirectAttributes atributos) {
		Fornecedor fornecedorQueSeraAlterado = repFornecedor.findById(id).get();
	
		fornecedorQueSeraAlterado.setVisivel("N");
		
		repFornecedor.save(fornecedorQueSeraAlterado);		 
		atributos.addFlashAttribute("mensagemStatus", "Fornecedor inativado com sucesso");
		return "redirect:/fornecedor";
	}
}
