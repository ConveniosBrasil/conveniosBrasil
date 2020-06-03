package br.com.fapen.conveniosBrasil.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fapen.conveniosBrasil.models.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{
	public Page<Fornecedor> findByNomeFantasiaContainingIgnoreCaseAndVisivel(String nomeFantasia, String visivel, Pageable paginacao);
	public Page<Fornecedor> findByVisivelOrderByIdAsc(String visivel, Pageable paginacao);
	public Page<Fornecedor> findByOrderByIdAsc(Pageable paginacao);
	public Page<Fornecedor> findByNomeFantasiaContainingIgnoreCase(String nomeFantasia, Pageable paginacao);
	public Fornecedor findByCnpj(String cnpj);
	public Fornecedor findByEmail(String email);
	public Fornecedor findByVisivel(String visivel);
}
