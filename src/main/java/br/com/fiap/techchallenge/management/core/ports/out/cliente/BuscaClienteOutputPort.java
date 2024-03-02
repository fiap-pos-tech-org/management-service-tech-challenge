package br.com.fiap.techchallenge.management.core.ports.out.cliente;

import br.com.fiap.techchallenge.management.core.dtos.ClienteDTO;

public interface BuscaClienteOutputPort {
    ClienteDTO buscar(String cpf);

    ClienteDTO buscar(Long id);
}
