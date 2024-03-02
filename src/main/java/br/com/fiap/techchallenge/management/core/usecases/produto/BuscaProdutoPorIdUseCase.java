package br.com.fiap.techchallenge.management.core.usecases.produto;

import br.com.fiap.techchallenge.management.core.dtos.ProdutoDTO;
import br.com.fiap.techchallenge.management.core.ports.in.produto.BuscaProdutoPorIdInputPort;
import br.com.fiap.techchallenge.management.core.ports.out.produto.BuscaProdutoPorIdOutputPort;

public class BuscaProdutoPorIdUseCase implements BuscaProdutoPorIdInputPort {

    BuscaProdutoPorIdOutputPort buscaProdutoPorIdOutputPort;

    public BuscaProdutoPorIdUseCase(BuscaProdutoPorIdOutputPort buscaProdutoPorIdOutputPort) {
        this.buscaProdutoPorIdOutputPort = buscaProdutoPorIdOutputPort;
    }

    @Override
    public ProdutoDTO buscarPorId(Long id) {
        return buscaProdutoPorIdOutputPort.buscarPorId(id);
    }
}
