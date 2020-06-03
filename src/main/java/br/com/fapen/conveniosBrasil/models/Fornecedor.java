package br.com.fapen.conveniosBrasil.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "fornecedor")
public class Fornecedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "razao_social", length = 255)	
	private String razaoSocial;
	
	@Column(name = "nome_fantasia", length = 255)	
	private String nomeFantasia;
	
	@Column(name = "nome_responsavel", length = 255)	
	private String nomeResponsavel;
	
	@Column(length = 15)
	private String cnpj;
	
	@Column(length = 11)
	private String telefone;
	
	@Column(length = 255)
	private String email;
	
	@Column(length = 8)
	private String cep;
	
	@Column(length = 255)
	private String rua;
	
	@Column(length = 10)
	private String numero;
	
	@Column(length = 20)
	private String complemento;
	
	@Column(length = 100)
	private String bairro;
	
	@Column(length = 100)
	private String cidade;
	
	@Column(length = 2)
	private String estado;
	
	@Column(length = 1)
	private String visivel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj.replaceAll("[^0-9]", "");
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone.replaceAll("[^0-9]", "");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep.replaceAll("[^0-9]", "");
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getVisivel() {
		return visivel;
	}

	public void setVisivel(String visivel) {
		this.visivel = visivel;
	}	
}
