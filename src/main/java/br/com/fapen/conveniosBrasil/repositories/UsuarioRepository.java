package br.com.fapen.conveniosBrasil.repositories;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fapen.conveniosBrasil.models.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
	public Page<Usuario> findByOrderByIdAsc(Pageable paginacao);
	public Page<Usuario> findByNomeContainingIgnoreCase(String nome, Pageable paginacao);
	public Usuario findByUsername(String username);
	public Usuario findByCpf(String cpf);
	public Usuario findByToken(String token);
	public boolean existsByUsername(String username);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE usuario SET senha = :senha, token = :token WHERE id = :id", nativeQuery = true)
	public void alterarSenha(@Param("senha") String novaSenha, @Param("token") String token, @Param("id") Long id);
}
