package br.com.fapen.conveniosBrasil.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fapen.conveniosBrasil.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	public Page<Produto> findByNomeProdutoContainingIgnoreCaseAndVisivel(String nomeProduto, String visivel, Pageable paginacao);
	public Page<Produto> findByVisivelOrderByIdAsc(String visivel, Pageable paginacao);
	public Page<Produto> findByNomeProdutoContainingIgnoreCase(String nomeProduto, Pageable paginacao);
	public Page<Produto> findByOrderByIdAsc(Pageable paginacao);
	public Produto findByNomeProduto(String nomeProduto);
}
