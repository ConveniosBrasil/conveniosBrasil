package br.com.fapen.conveniosBrasil.models;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name = "usuario")
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

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
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate data_nascimento;
	
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
	
	@Column(name="email", length = 255)
	private String username;
	
	@Column(name="senha", length = 255, updatable = false)
	private String password;
	
	@Column(length = 255, updatable = false)
	private String token;
	
	@Column(name="caminho_foto", length = 255)
	private String foto;
	
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

	public LocalDate getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getVisivel() {
		return visivel;
	}

	public void setVisivel(String visivel) {
		this.visivel = visivel;
	}
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_perfil", joinColumns = @JoinColumn(name = "id_usuario"),
				inverseJoinColumns = @JoinColumn(name = "id_perfil"))
	private List<Perfil> authorities = new ArrayList<Perfil>();
	
	public void setAuthorities(List<Perfil> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<Perfil> getAuthorities() {
		return authorities;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		if (this.getVisivel().equals("S"))
			return true;
		else
			return false;
	}
	
	public boolean temPerfil(String nomePerfil) {
		boolean resultado = false;
		
		for (Perfil pf : this.authorities) {
			if (pf.getAuthority().equals(nomePerfil)) {
				resultado = true;
				break;
			}	
		}	
		return resultado;
	}
}
