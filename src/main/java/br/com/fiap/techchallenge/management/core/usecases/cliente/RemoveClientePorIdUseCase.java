package br.com.fiap.techchallenge.management.core.usecases.cliente;

import br.com.fiap.techchallenge.management.core.dtos.ClienteDTO;
import br.com.fiap.techchallenge.management.core.ports.in.cliente.RemoveClientePorIdInputPort;
import br.com.fiap.techchallenge.management.core.ports.out.cliente.RemoveClientePorIdOutputPort;

public class RemoveClientePorIdUseCase implements RemoveClientePorIdInputPort {

    RemoveClientePorIdOutputPort removeClientePorIdOutputPort;

    public RemoveClientePorIdUseCase(RemoveClientePorIdOutputPort removeClientePorIdOutputPort) {
        this.removeClientePorIdOutputPort = removeClientePorIdOutputPort;
    }

    @Override
    public ClienteDTO removerPorId(Long id) {
        return removeClientePorIdOutputPort.removerPorId(id);
    }
}
