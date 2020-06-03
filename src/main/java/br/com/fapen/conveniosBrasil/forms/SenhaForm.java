package br.com.fapen.conveniosBrasil.forms;

import br.com.fapen.conveniosBrasil.models.Usuario;

public class SenhaForm {
	private Usuario usuario;
	private String repetirSenha;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getRepetirSenha() {
		return repetirSenha;
	}
	
	public void setRepetirSenha(String repetirSenha) {
		this.repetirSenha = repetirSenha;
	}

}
