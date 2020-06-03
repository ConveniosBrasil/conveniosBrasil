package br.com.fapen.conveniosBrasil.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import br.com.fapen.conveniosBrasil.forms.SenhaForm;
import br.com.fapen.conveniosBrasil.forms.UsuarioForm;
import br.com.fapen.conveniosBrasil.models.Perfil;
import br.com.fapen.conveniosBrasil.models.Usuario;
import br.com.fapen.conveniosBrasil.repositories.Paginacao;
import br.com.fapen.conveniosBrasil.repositories.UsuarioRepository;

@Repository
public class UsuarioService implements UserDetailsService {
	@Autowired
	private UsuarioRepository repUsuario;

	public String geraTokenUsuario(Usuario usuario) {
		String dadosUsuario = usuario.getCpf() + usuario.getUsername() + usuario.getPassword();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		BigInteger hash = new BigInteger(1, md.digest(dadosUsuario.getBytes()));
		return hash.toString(16);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuarioEncontrado = repUsuario.findByUsername(username);
		return usuarioEncontrado;
	}

	public Page<Usuario> listar(String busca, Integer pagina) {
		if (busca.equals("")) {
			return repUsuario.findByOrderByIdAsc(Paginacao.getPaginacao(pagina));
		} else
			return repUsuario.findByNomeContainingIgnoreCase(busca, Paginacao.getPaginacao(pagina));
	}

	public void salvar(UsuarioForm formulario) {
		if (formulario.isInclusao()) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCripto = encoder.encode(formulario.getUsuario().getPassword());
			formulario.getUsuario().setPassword(senhaCripto);
			formulario.getUsuario().setToken(geraTokenUsuario(formulario.getUsuario()));
		}

		formulario.getUsuario().getAuthorities().clear();
		for (Perfil p : formulario.getListaPerfil()) {
			if (p.getAuthority() != null) {
				formulario.getUsuario().getAuthorities().add(p);
			}
		}

		repUsuario.save(formulario.getUsuario());
	}

	public void salvarSenhaNova(SenhaForm senhaForm, Long id) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaCripto = encoder.encode(senhaForm.getUsuario().getPassword());
		
		repUsuario.alterarSenha(senhaCripto, geraTokenUsuario(senhaForm.getUsuario()), id);
	}
	
	public Usuario loadUserByToken(String token) {
		return repUsuario.findByToken(token);
	}
}
