package br.com.fiap.techchallenge.management.core.usecases.cliente;

import br.com.fiap.techchallenge.management.core.dtos.ClienteDTO;
import br.com.fiap.techchallenge.management.core.ports.in.cliente.CadastraClienteInputPort;
import br.com.fiap.techchallenge.management.core.ports.out.cliente.CadastraClienteOutputPort;


public class CadastraClienteUseCase implements CadastraClienteInputPort {

    private final CadastraClienteOutputPort cadastraClienteOutputPort;

    public CadastraClienteUseCase(CadastraClienteOutputPort cadastraClienteOutputPort) {
        this.cadastraClienteOutputPort = cadastraClienteOutputPort;
    }

    @Override
    public ClienteDTO cadastrar(ClienteDTO cliente) {
        return cadastraClienteOutputPort.cadastrar(cliente);
    }
}
