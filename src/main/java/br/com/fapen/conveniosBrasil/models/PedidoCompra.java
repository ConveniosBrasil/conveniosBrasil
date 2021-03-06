package br.com.fapen.conveniosBrasil.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.fapen.conveniosBrasil.enums.CondicaoPagtoEnum;
import br.com.fapen.conveniosBrasil.enums.StatusPedidoEnum;

@Entity(name = "pedido_compra")
public class PedidoCompra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Fornecedor fornecedor;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_entrega")
	private LocalDate dataEntrega;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "condicao_pagto")
	private CondicaoPagtoEnum condicaoPagamento;

	@Enumerated(EnumType.STRING)
	private StatusPedidoEnum status;
	
	private String observacao;
	
	@Column(name = "valor_total")
	private BigDecimal valorTotal;
	
	private String visivel;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemPedidoCompra> itens = new ArrayList<ItemPedidoCompra>();
	
	public PedidoCompra() {
		this.status = StatusPedidoEnum.EM_DIGITACAO;
		this.valorTotal = BigDecimal.ZERO;
		this.dataEntrega = LocalDate.now().plusDays(10);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public CondicaoPagtoEnum getCondicaoPagamento() {
		return condicaoPagamento;
	}

	public void setCondicaoPagamento(CondicaoPagtoEnum condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}

	public StatusPedidoEnum getStatus() {
		return status;
	}

	public void setStatus(StatusPedidoEnum status) {
		this.status = status;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getVisivel() {
		return visivel;
	}

	public void setVisivel(String visivel) {
		this.visivel = visivel;
	}

	public List<ItemPedidoCompra> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoCompra> itens) {
		this.itens = itens;
	}
}
