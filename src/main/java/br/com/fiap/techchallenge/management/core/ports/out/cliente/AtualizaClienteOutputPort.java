package br.com.fiap.techchallenge.management.core.ports.out.cliente;

import br.com.fiap.techchallenge.management.core.dtos.ClienteDTO;

public interface AtualizaClienteOutputPort {
    ClienteDTO atualizar(ClienteDTO cliente, Long id);
}
