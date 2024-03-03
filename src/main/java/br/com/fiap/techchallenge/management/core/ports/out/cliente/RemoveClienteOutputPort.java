package br.com.fiap.techchallenge.management.core.ports.out.cliente;

import br.com.fiap.techchallenge.management.core.dtos.ClienteDTO;

public interface RemoveClienteOutputPort {

    ClienteDTO remover(String cpf);
}
