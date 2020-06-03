package br.com.fapen.conveniosBrasil.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fapen.conveniosBrasil.models.Corretor;

public interface CorretorRepository  extends JpaRepository<Corretor, Long> {
	public Page<Corretor> findByVisivelOrderByIdAsc(String visivel, Pageable paginacao);
	public Page<Corretor> findByNomeContainingIgnoreCaseAndVisivel(String nome, String visivel, Pageable paginacao);
	public Page<Corretor> findByOrderByIdAsc(Pageable paginacao);
	public Page<Corretor> findByNomeContainingIgnoreCase(String nome, Pageable paginacao);
	public Corretor findByCpf(String cpf);
	public Corretor findByEmail(String email);
}
