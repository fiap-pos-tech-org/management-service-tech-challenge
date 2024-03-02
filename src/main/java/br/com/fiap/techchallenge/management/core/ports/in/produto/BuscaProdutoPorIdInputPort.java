package br.com.fiap.techchallenge.management.core.ports.in.produto;

import br.com.fiap.techchallenge.management.core.dtos.ProdutoDTO;

public interface BuscaProdutoPorIdInputPort {

    ProdutoDTO buscarPorId(Long id);
}
