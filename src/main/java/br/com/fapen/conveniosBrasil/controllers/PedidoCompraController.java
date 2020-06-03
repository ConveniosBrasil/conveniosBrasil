package br.com.fapen.conveniosBrasil.controllers;

import java.io.ByteArrayInputStream;
import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fapen.conveniosBrasil.enums.CondicaoPagtoEnum;
import br.com.fapen.conveniosBrasil.enums.StatusPedidoEnum;
import br.com.fapen.conveniosBrasil.forms.PedidoCompraForm;
import br.com.fapen.conveniosBrasil.forms.PedidoFiltroForm;
import br.com.fapen.conveniosBrasil.models.ItemPedidoCompra;
import br.com.fapen.conveniosBrasil.models.PedidoCompra;
import br.com.fapen.conveniosBrasil.repositories.FornecedorRepository;
import br.com.fapen.conveniosBrasil.repositories.PedidoCompraRepository;
import br.com.fapen.conveniosBrasil.repositories.ProdutoRepository;
import br.com.fapen.conveniosBrasil.services.PedidoCompraService;
import br.com.fapen.conveniosBrasil.validations.PedidoCompraFormValidator;

@Controller
@RequestMapping("/pedido")
public class PedidoCompraController {

	@Autowired
	private PedidoCompraRepository repPedidoCompra;

	@Autowired
	private PedidoCompraService servicoPedidoCompra;
	
	@Autowired
	private FornecedorRepository repFornecedor;

	@Autowired
	private ProdutoRepository repProduto;
	
	@Autowired
	private PedidoCompraFormValidator validadorFormPedidoCompra;

	@InitBinder("pedidoCompraForm")
	protected void init(WebDataBinder binder) {
		binder.setValidator(validadorFormPedidoCompra);
	}
	
	@RequestMapping(value = "/novoitem", method = RequestMethod.POST, name = "novoItemPedidoUrl")
	public ModelAndView formularioItem(PedidoCompraForm pedidoCompraForm) {
		pedidoCompraForm.getItemPedidoCompra().add(new ItemPedidoCompra());
		ModelAndView mav = new ModelAndView("menuadmin/pedidoCompra/cadastros-item");
		mav.addObject("listaProdutos", repProduto.findAll());
		return mav;
	}

	@RequestMapping(value = "/deleteitem/{linha}", method = RequestMethod.POST, name = "deleteItemPedidoUrl")
	public ModelAndView deleteItem(@PathVariable int linha, PedidoCompraForm pedidoCompraForm) {
		pedidoCompraForm.getItemPedidoCompra().remove(linha);
		ModelAndView mav = new ModelAndView("menuadmin/pedidoCompra/cadastros-item");
		mav.addObject("listaProdutos", repProduto.findAll());
		return mav;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET, name = "novoPedidoCompraUrl")
	public ModelAndView formulario(PedidoCompraForm pedidoCompraForm) {
		ModelAndView mav = new ModelAndView("menuadmin/pedidoCompra/cadastros");
		mav.addObject("listaFornecedores", repFornecedor.findAll());
		mav.addObject("listaProdutos", repProduto.findAll());
		mav.addObject("listaCondicaoPagto", CondicaoPagtoEnum.values());
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, name = "salvarPedidoCompraUrl")
	public ModelAndView salvarNoBanco(@Valid PedidoCompraForm pedidoCompraForm, BindingResult resultadoValidacao,
			RedirectAttributes atributos) {

		if (resultadoValidacao.hasErrors()) {
			return formulario(pedidoCompraForm);
		}

		servicoPedidoCompra.salvar(pedidoCompraForm);
		atributos.addFlashAttribute("mensagemStatus", "Pedido salvo com sucesso");
		return new ModelAndView("redirect:/pedido");
	}

	@RequestMapping(method = RequestMethod.GET, name = "listarPedidoCompraUrl")
	public ModelAndView listar(PedidoFiltroForm filtroForm, Principal principal) {

		ModelAndView mav = new ModelAndView("menuadmin/pedidoCompra/lista");
		Page<PedidoCompra> dados = servicoPedidoCompra.listar(filtroForm, principal);
		mav.addObject("listaPaginada", dados);
		mav.addObject("pedidoFiltroForm", filtroForm);
		mav.addObject("listaDeStatus", StatusPedidoEnum.values());
		return mav;
	}

	@RequestMapping(value = "/{id}/editar", method = RequestMethod.GET, name = "alterarPedidoCompraUrl")
	public ModelAndView alterar(@PathVariable Long id, Model model) {
		PedidoCompra registro = servicoPedidoCompra.findById(id);

		PedidoCompraForm formulario = new PedidoCompraForm(registro);
		
		model.addAttribute(formulario);
		return formulario(formulario);
	}
	
	@RequestMapping(value = "{id}/visualizar", method = RequestMethod.GET, name = "visualizarPedidoCompraUrl")
	public ModelAndView detalhes(@PathVariable Long id) {
		PedidoCompra registro = servicoPedidoCompra.findById(id);
		
		ModelAndView mav = new ModelAndView("menuadmin/pedidoCompra/visualizar");
		mav.addObject("registro", registro);
		return mav;
	}
	
	@RequestMapping(value = "/{id}/inativar", method = RequestMethod.POST, name = "inativarPedidoCompraUrl")
	public String inativar(@PathVariable Long id, RedirectAttributes atributos) {
		PedidoCompra pedidoCompraQueSeraAlterado = repPedidoCompra.findById(id).get();
		pedidoCompraQueSeraAlterado.setVisivel("N");
		
		repPedidoCompra.save(pedidoCompraQueSeraAlterado);		 
		atributos.addFlashAttribute("mensagemStatus", "Pedido de compra inativado com sucesso");
		return "redirect:/pedido";
	}
	
	@RequestMapping(value = "/{id}/ativar", method = RequestMethod.POST, name = "ativarPedidoCompraUrl")
	public String ativar(@PathVariable Long id, RedirectAttributes atributos) {
		PedidoCompra pedidoCompraQueSeraAlterado = repPedidoCompra.findById(id).get();
		pedidoCompraQueSeraAlterado.setVisivel("S");
		
		repPedidoCompra.save(pedidoCompraQueSeraAlterado);		 
		atributos.addFlashAttribute("mensagemStatus", "Pedido de compra ativado com sucesso");
		return "redirect:/pedido";
	}
	
	@RequestMapping(value = "/{id}/pdf", method = RequestMethod.GET, name = "geraPdfPedidoUrl", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> imprimir(@PathVariable Long id) {
		PedidoCompra pedCompra = servicoPedidoCompra.findById(id);
		ByteArrayInputStream pdfEmMemoria = servicoPedidoCompra.gerarPdf(pedCompra);
		HttpHeaders cabecalho = new HttpHeaders();
		cabecalho.add("Content-Disposition", "inline; filename=pedido_" + id + ".pdf");
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).headers(cabecalho)
				.body(new InputStreamResource(pdfEmMemoria));
	}
}
