package br.com.fiap.techchallenge.management.core.ports.in.produto;

import br.com.fiap.techchallenge.management.core.dtos.AtualizaImagemProdutoDTO;
import br.com.fiap.techchallenge.management.core.dtos.ProdutoDTO;

public interface AtualizaImagemProdutoInputPort {

    ProdutoDTO atualizar(AtualizaImagemProdutoDTO imagemIn, Long id);
}
