package br.com.fiap.techchallenge.management.core.ports.in.cliente;

import br.com.fiap.techchallenge.management.core.dtos.ClienteDTO;
import java.util.List;

public interface BuscaTodosClientesInputPort {
    List<ClienteDTO> buscarTodos();
}
