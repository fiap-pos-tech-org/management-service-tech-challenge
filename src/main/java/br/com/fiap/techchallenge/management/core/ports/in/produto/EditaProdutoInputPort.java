package br.com.fiap.techchallenge.management.core.ports.in.produto;

import br.com.fiap.techchallenge.management.core.dtos.ProdutoDTO;

public interface EditaProdutoInputPort {

    ProdutoDTO editar(ProdutoDTO produto, Long id);
}
