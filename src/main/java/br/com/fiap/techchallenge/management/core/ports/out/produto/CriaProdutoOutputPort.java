package br.com.fiap.techchallenge.management.core.ports.out.produto;

import br.com.fiap.techchallenge.management.core.dtos.ProdutoDTO;
public interface CriaProdutoOutputPort {

    ProdutoDTO criar(ProdutoDTO produto);
}
