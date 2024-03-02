package br.com.fiap.techchallenge.management.core.ports.out.produto;

import br.com.fiap.techchallenge.management.core.dtos.AtualizaImagemProdutoDTO;
import br.com.fiap.techchallenge.management.core.dtos.ProdutoDTO;

public interface AtualizaImagemProdutoOutputPort {

    ProdutoDTO atualizar(AtualizaImagemProdutoDTO imagenIn, Long id);
}
