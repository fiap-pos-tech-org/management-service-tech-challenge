package br.com.fiap.techchallenge.management.core.ports.out.produto;

import br.com.fiap.techchallenge.management.core.dtos.ProdutoDTO;
import java.util.List;

public interface BuscaTodosProdutosOutputPort {

    List<ProdutoDTO> buscarTodos();
}
