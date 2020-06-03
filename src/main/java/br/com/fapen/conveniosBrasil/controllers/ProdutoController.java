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

import br.com.fapen.conveniosBrasil.models.Produto;
import br.com.fapen.conveniosBrasil.models.Usuario;
import br.com.fapen.conveniosBrasil.repositories.Paginacao;
import br.com.fapen.conveniosBrasil.repositories.ProdutoRepository;
import br.com.fapen.conveniosBrasil.repositories.UsuarioRepository;
import br.com.fapen.conveniosBrasil.validations.ProdutoValidator;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	@Autowired
	private ProdutoRepository repProduto;

	@Autowired
	private UsuarioRepository repUsuario;
	
	@Autowired
	private ProdutoValidator validadorProduto;

	@InitBinder("produto")
	protected void init(WebDataBinder binder) {
		binder.setValidator(validadorProduto);
	}

	@RequestMapping(value = "/novo", method = RequestMethod.GET, name = "novoProdutoUrl")
	public String formulario(Produto produto) {
		return "menuadmin/produto/cadastro";
	}

	@RequestMapping(method = RequestMethod.POST, name = "salvarProdutoUrl")
	public String salvarNoBanco(@Valid Produto produto, BindingResult resultadoValidacao,
			RedirectAttributes atributos) {

		if (resultadoValidacao.hasErrors()) {
			return formulario(produto);
		}

		repProduto.save(produto);
		atributos.addFlashAttribute("mensagemStatus", "Produto salvo com sucesso");
		return "redirect:/produto";
	}

	@RequestMapping(method = RequestMethod.GET, name = "listarProdutoUrl")
	public ModelAndView listar(@RequestParam(defaultValue = "1") Integer pagina,
			@RequestParam(defaultValue = "") String busca, Principal usuarioPrincipal) {
		Page<Produto> listaDeProdutoCadastrados;

		Usuario perfilUsuario = repUsuario.findByUsername(usuarioPrincipal.getName());
		if (busca.equals("") && perfilUsuario.temPerfil("ROLE_ADMIN")) {
			listaDeProdutoCadastrados = repProduto.findByOrderByIdAsc(Paginacao.getPaginacao(pagina));
		} else if (busca.equals("") && !perfilUsuario.temPerfil("ROLE_ADMIN")) {
			listaDeProdutoCadastrados = repProduto.findByVisivelOrderByIdAsc("S", Paginacao.getPaginacao(pagina));
		}else if (!busca.equals("") && perfilUsuario.temPerfil("ROLE_ADMIN")) {
			listaDeProdutoCadastrados = repProduto.findByNomeProdutoContainingIgnoreCase(busca, Paginacao.getPaginacao(pagina));
		} else
			listaDeProdutoCadastrados = repProduto.findByNomeProdutoContainingIgnoreCaseAndVisivel(busca, "S",
					Paginacao.getPaginacao(pagina));

		ModelAndView mav = new ModelAndView("menuadmin/produto/lista");
		mav.addObject("listaPaginada", listaDeProdutoCadastrados);
		mav.addObject("busca", busca);

		return mav;
	}

	@RequestMapping(value = "/{id}/editar", method = RequestMethod.GET, name = "alterarProdutoUrl")
	public String alterar(@PathVariable Long id, Model model) {
		Produto produtoQueSeraAlterado = repProduto.findById(id).get();
		
		model.addAttribute(produtoQueSeraAlterado);
		return formulario(produtoQueSeraAlterado);
	}

	@RequestMapping(value = "/{id}/visualizar", method = RequestMethod.GET, name = "visualizarProdutoUrl")
	public ModelAndView visualizar(@PathVariable Long id) {
		Produto produtoQueSeraVisualizado = repProduto.findById(id).get();

		ModelAndView mav = new ModelAndView("menuadmin/produto/visualizar");
		mav.addObject("produto", produtoQueSeraVisualizado);

		return mav;
	}
	
	@RequestMapping(value = "/{id}/ativar", method = RequestMethod.POST, name = "ativarProdutoUrl")
	public String ativar(@PathVariable Long id, RedirectAttributes atributos) {
		Produto produtoQueSeraAlterado = repProduto.findById(id).get();
		produtoQueSeraAlterado.setVisivel("S");
		
		repProduto.save(produtoQueSeraAlterado);		 
		atributos.addFlashAttribute("mensagemStatus", "Produto ativado com sucesso");
		return "redirect:/produto";
	}

	@RequestMapping(value = "/{id}/inativar", method = RequestMethod.POST, name = "inativarProdutoUrl")
	public String inativar(@PathVariable Long id, RedirectAttributes atributos) {
		Produto produtoQueSeraAlterado = repProduto.findById(id).get();
		produtoQueSeraAlterado.setVisivel("N");
		
		repProduto.save(produtoQueSeraAlterado);		 
		atributos.addFlashAttribute("mensagemStatus", "Produto inativado com sucesso");
		return "redirect:/produto";
	}
}
