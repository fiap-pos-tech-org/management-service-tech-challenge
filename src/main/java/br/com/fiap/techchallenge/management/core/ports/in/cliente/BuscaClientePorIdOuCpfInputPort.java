package br.com.fiap.techchallenge.management.core.ports.in.cliente;

import br.com.fiap.techchallenge.management.core.dtos.ClienteDTO;

public interface BuscaClientePorIdOuCpfInputPort {
    ClienteDTO buscar(String cpf);

    ClienteDTO buscar(Long id);
}
