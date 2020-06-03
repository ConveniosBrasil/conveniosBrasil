package br.com.fapen.conveniosBrasil.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity(name = "perfil")
public class Perfil implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="id_perfil", length = 45)
	private String authority;
	
	@Column(length = 255)
	private String descricao;
	
	@Column(length = 1)
	private String visivel;
	
	public Perfil() {
	}
	
	public Perfil(String authority, String descricao) {
		this.authority = authority;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVisivel() {
		return visivel;
	}

	public void setVisivel(String visivel) {
		this.visivel = visivel;
	}

	@Override
	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
