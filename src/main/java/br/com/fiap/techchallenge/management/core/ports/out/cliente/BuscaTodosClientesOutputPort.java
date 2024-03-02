package br.com.fiap.techchallenge.management.core.ports.out.cliente;

import br.com.fiap.techchallenge.management.core.dtos.ClienteDTO;
import java.util.List;

public interface BuscaTodosClientesOutputPort {
    List<ClienteDTO> buscarTodos();
}
