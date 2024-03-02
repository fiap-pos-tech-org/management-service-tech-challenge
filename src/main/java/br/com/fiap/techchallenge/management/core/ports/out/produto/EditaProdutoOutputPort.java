package br.com.fiap.techchallenge.management.core.ports.out.produto;

import br.com.fiap.techchallenge.management.core.dtos.ProdutoDTO;
public interface EditaProdutoOutputPort {

    ProdutoDTO editar(ProdutoDTO produto, Long id);
}
