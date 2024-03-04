package br.com.fiap.techchallenge.management.core.usecases.cliente;

import br.com.fiap.techchallenge.management.core.dtos.ClienteDTO;
import br.com.fiap.techchallenge.management.core.ports.in.cliente.RemoveClienteInputPort;
import br.com.fiap.techchallenge.management.core.ports.out.cliente.RemoveClienteOutputPort;

public class RemoveClienteUseCase implements RemoveClienteInputPort {

    RemoveClienteOutputPort removeClienteOutputPort;

    public RemoveClienteUseCase(RemoveClienteOutputPort removeClienteOutputPort) {
        this.removeClienteOutputPort = removeClienteOutputPort;
    }

    @Override
    public ClienteDTO remover(String cpf) {
        return removeClienteOutputPort.remover(cpf);
    }
}
