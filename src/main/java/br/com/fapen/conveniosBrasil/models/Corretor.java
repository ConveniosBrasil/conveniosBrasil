package br.com.fapen.conveniosBrasil.models;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity(name = "corretor")
public class Corretor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 255)	
	private String nome;
	
	@Column(length = 11)
	private String cpf;
	
	@Column(length = 10)
	private String rg;
	
	@Column(length = 1)
	private String sexo;
	
	@Column(name = "datanascimento")
	@DateTimeFormat( iso = ISO.DATE)
	private LocalDate dataNascimento;
	
	@Column(length = 11)
	private String telefone;
	
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
	
	@Column(length = 255)
	private String email;
	
	@Column(length = 1)
	private String visivel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf.replaceAll("[^0-9]", "");
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone.replaceAll("[^0-9]", "");
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

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getVisivel() {
		return visivel;
	}

	public void setVisivel(String visivel) {
		this.visivel = visivel;
	}
}
