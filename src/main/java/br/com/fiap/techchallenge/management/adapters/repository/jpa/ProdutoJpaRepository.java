package br.com.fiap.techchallenge.management.adapters.repository.jpa;

import br.com.fiap.techchallenge.management.adapters.repository.models.Produto;
import br.com.fiap.techchallenge.management.core.domain.entities.enums.CategoriaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoJpaRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoria(CategoriaEnum categoriaEnum);

}
