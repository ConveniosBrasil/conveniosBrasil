package br.com.fapen.conveniosBrasil.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fapen.conveniosBrasil.forms.UsuarioForm;
import br.com.fapen.conveniosBrasil.models.Usuario;
import br.com.fapen.conveniosBrasil.repositories.PerfilRepository;
import br.com.fapen.conveniosBrasil.repositories.UsuarioRepository;
import br.com.fapen.conveniosBrasil.services.ArquivoService;
import br.com.fapen.conveniosBrasil.services.UsuarioService;
import br.com.fapen.conveniosBrasil.validations.UsuarioValidator;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService servicoUsuario;

	@Autowired
	private ArquivoService gravarArquivo;
	
	@Autowired
	private UsuarioRepository repUsuario;
	
	@Autowired
	private PerfilRepository repPerfil;

	@Autowired
	private UsuarioValidator validadorUsuario;

	@InitBinder("usuarioForm")
	protected void init(WebDataBinder binder) {
		binder.setValidator(validadorUsuario);
	}

	@RequestMapping(value = "/novo", method = RequestMethod.GET, name = "novoUsuarioUrl")
	public ModelAndView formulario(UsuarioForm usuarioForm) {
		ModelAndView mav = new ModelAndView("menuadmin/usuario/cadastro");
		mav.addObject("listaDePerfil", repPerfil.findAll());
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, name = "salvarUsuarioUrl")
	public ModelAndView salvarNoBanco(@Valid UsuarioForm usuarioForm, BindingResult resultadoValidacao,
			RedirectAttributes atributos) {

		if (resultadoValidacao.hasErrors()) {
			return formulario(usuarioForm);
		}
		
		servicoUsuario.salvar(usuarioForm);
		atributos.addFlashAttribute("mensagemStatus", "Usuario salvo com sucesso");
		return new ModelAndView("redirect:/usuario");
	}

	@RequestMapping(method = RequestMethod.GET, name = "listarUsuarioUrl")
	public ModelAndView listar(@RequestParam(defaultValue = "1") Integer pagina,
			@RequestParam(defaultValue = "") String busca) {
		Page<Usuario> dados = servicoUsuario.listar(busca, pagina);
		
		ModelAndView mav = new ModelAndView("menuadmin/usuario/lista");
		mav.addObject("listaPaginada", dados);
		mav.addObject("busca", busca);

		return mav;
	}

	@RequestMapping(value = "/{id}/editar", method = RequestMethod.GET, name = "alterarUsuarioUrl")
	public ModelAndView alterar(@PathVariable Long id, Model model) {
		Usuario usuarioQueSeraAlterado = repUsuario.findById(id).get();

		UsuarioForm formulario = new UsuarioForm(usuarioQueSeraAlterado);
		formulario.setInclusao(false);
		
		model.addAttribute(formulario);
		return formulario(formulario);
	}

	@RequestMapping(value = "/{id}/visualizar", method = RequestMethod.GET, name = "visualizarUsuarioUrl")
	public ModelAndView visualizar(@PathVariable Long id) {
		Usuario usuarioQueSeraVisualizado = repUsuario.findById(id).get();

		ModelAndView mav = new ModelAndView("menuadmin/usuario/visualizar");
		mav.addObject("usuario", usuarioQueSeraVisualizado);

		return mav;
	}

	@RequestMapping(value = "/{id}/deletar", method = RequestMethod.POST, name = "inativarUsuarioUrl")
	public String inativar(@PathVariable Long id, RedirectAttributes atributos) {
		Usuario usuarioQueSeraAlterado = repUsuario.findById(id).get();
		
		usuarioQueSeraAlterado.setVisivel("N");

		repUsuario.save(usuarioQueSeraAlterado);		 
		atributos.addFlashAttribute("mensagemStatus", "Usuario inativado com sucesso");
		return "redirect:/usuario";

	}
	
	@RequestMapping(value = "/{id}/inativar", method = RequestMethod.POST, name = "ativarUsuarioUrl")
	public String ativar(@PathVariable Long id, RedirectAttributes atributos) {
		Usuario usuarioQueSeraAlterado = repUsuario.findById(id).get();
		
		usuarioQueSeraAlterado.setVisivel("S");

		repUsuario.save(usuarioQueSeraAlterado);		 
		atributos.addFlashAttribute("mensagemStatus", "Usuario ativado com sucesso");
		return "redirect:/usuario";

	}
	
	@RequestMapping(value = "/perfil", method = RequestMethod.GET, name = "perfilUsuarioUrl")
	public ModelAndView perfil(Principal principal) {

		ModelAndView mav = new ModelAndView("menuadmin/usuario/perfil");
		Usuario usuarioLogado = repUsuario.findByUsername(principal.getName());
		mav.addObject("usuario", usuarioLogado);

		return mav;
	}
	
	@RequestMapping(value = "/perfil", method = RequestMethod.POST, name = "alterarFotoPerfilUrl")
	public String alterarFotoPerfil(MultipartFile foto, Principal principal) {
		
		String caminhoFoto = gravarArquivo.salvarEmDisco(foto);
		Usuario usuarioEncontrado = repUsuario.findByUsername(principal.getName());
		usuarioEncontrado.setFoto(caminhoFoto);
		repUsuario.save(usuarioEncontrado);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(usuarioEncontrado,
				usuarioEncontrado.getPassword(), usuarioEncontrado.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/usuario/perfil";

	}
}
