package br.com.fiap.techchallenge.management.core.ports.in.cliente;

import br.com.fiap.techchallenge.management.core.dtos.ClienteDTO;

public interface AtualizaClienteInputPort {
    ClienteDTO atualizar(ClienteDTO cliente, Long id);
}
