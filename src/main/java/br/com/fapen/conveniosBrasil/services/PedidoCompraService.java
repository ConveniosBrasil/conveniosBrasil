package br.com.fapen.conveniosBrasil.services;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fapen.conveniosBrasil.forms.PedidoCompraForm;
import br.com.fapen.conveniosBrasil.forms.PedidoFiltroForm;
import br.com.fapen.conveniosBrasil.models.ItemPedidoCompra;
import br.com.fapen.conveniosBrasil.models.PedidoCompra;
import br.com.fapen.conveniosBrasil.models.Usuario;
import br.com.fapen.conveniosBrasil.reports.PedidoCompraReport;
import br.com.fapen.conveniosBrasil.repositories.Paginacao;
import br.com.fapen.conveniosBrasil.repositories.PedidoCompraRepository;
import br.com.fapen.conveniosBrasil.repositories.UsuarioRepository;

@Service
public class PedidoCompraService {
	
	@Autowired
	private PedidoCompraRepository repPedidoCompra;
	
	@Autowired
	private UsuarioRepository repUsuario;
	
	@Autowired
	private PedidoCompraReport relImpressaoPedido;
	
	public void CalcularTotal(PedidoCompra pedido) {
		BigDecimal total = BigDecimal.ZERO;
		for (ItemPedidoCompra item : pedido.getItens()) {
			BigDecimal quantidade = new BigDecimal(item.getQuantidade());
			BigDecimal totalItem = item.getPrecoUnitario().multiply(quantidade);
			total = total.add(totalItem);
		}
		pedido.setValorTotal(total);
	}
	
	public void salvar(PedidoCompraForm pedidoCompraForm) {
		pedidoCompraForm.getPedidoCompra().getItens().clear();
		for (ItemPedidoCompra item : pedidoCompraForm.getItemPedidoCompra()) {
			item.setPedido(pedidoCompraForm.getPedidoCompra());
			pedidoCompraForm.getPedidoCompra().getItens().add(item);
		}
		this.CalcularTotal(pedidoCompraForm.getPedidoCompra());
		repPedidoCompra.save(pedidoCompraForm.getPedidoCompra());
	}
	
	public Page<PedidoCompra> listar(PageRequest paginacao) {
			return repPedidoCompra.findByOrderByIdAsc(paginacao);	
	}
	
	public Page<PedidoCompra> listar(PedidoFiltroForm filtro, Principal principal) {

		if (filtro.isNovoFiltro()) {
			filtro.setPagina(1);
		}
		Pageable paginacao = Paginacao.getPaginacao(filtro.getPagina());
		Usuario perfilUsuario = repUsuario.findByUsername(principal.getName());
		
		if ("RS".equals(filtro.getTipoFiltro()) && perfilUsuario.temPerfil("ROLE_ADMIN")) 
			return repPedidoCompra.findByFornecedor_razaoSocialContainingIgnoreCase(filtro.getRazaoSocial(), paginacao);
		else if ("RS".equals(filtro.getTipoFiltro()) && !perfilUsuario.temPerfil("ROLE_ADMIN")) 
			return repPedidoCompra.findByFornecedor_razaoSocialContainingIgnoreCaseAndVisivel(filtro.getRazaoSocial(), "S", paginacao);
		else if ("ST".equals(filtro.getTipoFiltro())&& perfilUsuario.temPerfil("ROLE_ADMIN")) 
			return repPedidoCompra.findByStatus(filtro.getStatus(), paginacao);
		else if ("ST".equals(filtro.getTipoFiltro())&& !perfilUsuario.temPerfil("ROLE_ADMIN")) 
			return repPedidoCompra.findByStatusAndVisivel(filtro.getStatus(), "S", paginacao);
		else if ("DT".equals(filtro.getTipoFiltro())&& perfilUsuario.temPerfil("ROLE_ADMIN"))
			return repPedidoCompra.findByDataEntregaBetween(filtro.getEntregaInicial(), filtro.getEntregaFinal(), paginacao);
		else if ("DT".equals(filtro.getTipoFiltro())&& !perfilUsuario.temPerfil("ROLE_ADMIN"))
			return repPedidoCompra.findByDataEntregaBetweenAndVisivel(filtro.getEntregaInicial(), filtro.getEntregaFinal(), "S", paginacao);
		else if (perfilUsuario.temPerfil("ROLE_ADMIN"))
			return repPedidoCompra.findByOrderByIdAsc(paginacao);
		else
			return repPedidoCompra.findByVisivelOrderByIdAsc("S", paginacao);	
	}
	
	public PedidoCompra findById(Long id) {
		Optional<PedidoCompra> optPedido = repPedidoCompra.findById(id);
		if (optPedido.isEmpty()) {
			return null;
		}
		return optPedido.get();
	}

	public void excluir(PedidoCompra pedido) {
		repPedidoCompra.delete(pedido);
	}
	
	public ByteArrayInputStream gerarPdf(PedidoCompra pedCompra) {
		return relImpressaoPedido.gerarPDF(pedCompra);
	}
}
