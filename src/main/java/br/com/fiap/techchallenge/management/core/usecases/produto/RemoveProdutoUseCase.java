package br.com.fiap.techchallenge.management.core.usecases.produto;

import br.com.fiap.techchallenge.management.core.dtos.ProdutoDTO;
import br.com.fiap.techchallenge.management.core.ports.in.produto.RemoveProdutoInputPort;
import br.com.fiap.techchallenge.management.core.ports.out.produto.RemoveProdutoOutputPort;

public class RemoveProdutoUseCase implements RemoveProdutoInputPort {

    RemoveProdutoOutputPort removeProdutoOutputPort;

    public RemoveProdutoUseCase(RemoveProdutoOutputPort removeProdutoOutputPort) {
        this.removeProdutoOutputPort = removeProdutoOutputPort;
    }

    @Override
    public ProdutoDTO remover(Long id) {
        return removeProdutoOutputPort.remover(id);
    }
}
