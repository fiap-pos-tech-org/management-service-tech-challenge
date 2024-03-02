package br.com.fiap.techchallenge.management.core.ports.in.produto;

import br.com.fiap.techchallenge.management.core.dtos.ProdutoDTO;
import java.util.List;

public interface BuscaTodosProdutosInputPort {

    List<ProdutoDTO> buscartodos();
}
