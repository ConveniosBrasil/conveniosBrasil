package br.com.fapen.conveniosBrasil.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fapen.conveniosBrasil.enums.StatusPedidoEnum;
import br.com.fapen.conveniosBrasil.models.PedidoCompra;

public interface PedidoCompraRepository extends JpaRepository<PedidoCompra, Long>{
	public Page<PedidoCompra> findByOrderByIdAsc(Pageable paginacao);
	public Page<PedidoCompra> findByVisivelOrderByIdAsc(String visivel, Pageable paginacao);
	public Page<PedidoCompra> findByFornecedor_razaoSocialContainingIgnoreCase(String razaoSocial, Pageable paginacao);
	public Page<PedidoCompra> findByFornecedor_razaoSocialContainingIgnoreCaseAndVisivel(String razaoSocial, String visivel, Pageable paginacao);
	public Page<PedidoCompra> findByStatus(StatusPedidoEnum status, Pageable paginacao);
	public Page<PedidoCompra> findByStatusAndVisivel(StatusPedidoEnum status, String visivel, Pageable paginacao);
	public Page<PedidoCompra> findByDataEntregaBetween(LocalDate dataInicial, LocalDate DataFinal, Pageable paginacao);
	public Page<PedidoCompra> findByDataEntregaBetweenAndVisivel(LocalDate dataInicial, LocalDate DataFinal, String visivel, Pageable paginacao);
}
