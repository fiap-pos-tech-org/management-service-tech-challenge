package br.com.fiap.techchallenge.management.core.ports.in.cliente;

import br.com.fiap.techchallenge.management.core.dtos.ClienteDTO;

public interface CadastraClienteInputPort {
    ClienteDTO cadastrar(ClienteDTO cliente);
}
