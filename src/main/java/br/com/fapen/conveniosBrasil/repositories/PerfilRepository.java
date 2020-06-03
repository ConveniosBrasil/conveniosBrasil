package br.com.fapen.conveniosBrasil.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fapen.conveniosBrasil.models.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long>{
	public Page<Perfil> findByAuthorityContainingIgnoreCaseAndVisivel(String descricao, String visivel, Pageable paginacao);
	public Page<Perfil> findByVisivelOrderByIdAsc(String visivel, Pageable paginacao);
	public Page<Perfil> findByOrderByIdAsc(Pageable paginacao);
	public Page<Perfil> findByAuthorityContainingIgnoreCase(String descricao, Pageable paginacao);
	public Perfil findByAuthority(String authority);
}
