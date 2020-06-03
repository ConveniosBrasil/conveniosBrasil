package br.com.fapen.conveniosBrasil.forms;

import java.time.LocalDate;

import br.com.fapen.conveniosBrasil.enums.StatusPedidoEnum;

public class PedidoFiltroForm {

	private String razaoSocial;
	private StatusPedidoEnum status;
	private LocalDate entregaInicial;
	private LocalDate entregaFinal;
	private String tipoFiltro;
	private Integer pagina;
	private boolean novoFiltro;

	public PedidoFiltroForm() {
		this.pagina = 1;
		this.novoFiltro = false;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public StatusPedidoEnum getStatus() {
		return status;
	}

	public void setStatus(StatusPedidoEnum status) {
		this.status = status;
	}

	public LocalDate getEntregaInicial() {
		return entregaInicial;
	}

	public void setEntregaInicial(LocalDate entregaInicial) {
		this.entregaInicial = entregaInicial;
	}

	public LocalDate getEntregaFinal() {
		return entregaFinal;
	}

	public void setEntregaFinal(LocalDate entregaFinal) {
		this.entregaFinal = entregaFinal;
	}

	public String getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(String tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

	public Integer getPagina() {
		return pagina;
	}

	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}

	public boolean isNovoFiltro() {
		return novoFiltro;
	}

	public void setNovoFiltro(boolean novoFiltro) {
		this.novoFiltro = novoFiltro;
	}
}
