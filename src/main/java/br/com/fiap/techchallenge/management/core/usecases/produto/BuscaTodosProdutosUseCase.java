package br.com.fiap.techchallenge.management.core.usecases.produto;

import br.com.fiap.techchallenge.management.core.dtos.ProdutoDTO;
import br.com.fiap.techchallenge.management.core.ports.in.produto.BuscaTodosProdutosInputPort;
import br.com.fiap.techchallenge.management.core.ports.out.produto.BuscaTodosProdutosOutputPort;

import java.util.List;

public class BuscaTodosProdutosUseCase implements BuscaTodosProdutosInputPort {

    BuscaTodosProdutosOutputPort buscaProdutoPorIdOutputPort;

    public BuscaTodosProdutosUseCase(BuscaTodosProdutosOutputPort buscaProdutoPorIdOutputPort) {
        this.buscaProdutoPorIdOutputPort = buscaProdutoPorIdOutputPort;
    }

    @Override
    public List<ProdutoDTO> buscartodos() {
        return buscaProdutoPorIdOutputPort.buscarTodos();
    }
}
