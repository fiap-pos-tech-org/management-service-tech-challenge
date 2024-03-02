package br.com.fiap.techchallenge.management.adapters.web;

import br.com.fiap.techchallenge.management.adapters.web.mappers.ClienteMapper;
import br.com.fiap.techchallenge.management.adapters.web.models.requests.ClienteRequest;
import br.com.fiap.techchallenge.management.adapters.web.models.responses.ClienteResponse;
import br.com.fiap.techchallenge.management.core.dtos.ClienteDTO;
import br.com.fiap.techchallenge.management.core.ports.in.cliente.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clientes", description = "APIs para gerenciamento de Clientes")
@RestController
@RequestMapping("/clientes")
public class ClienteController extends ControllerBase {


    private final AtualizaClienteInputPort atualizaClienteInputPort;
    private final BuscaClientePorIdOuCpfInputPort buscaClientePorIdOuCpfInputPort;
    private final BuscaTodosClientesInputPort buscaTodosClientesInputPort;
    private final CadastraClienteInputPort cadastraClienteInputPort;
    private final RemoveClientePorIdInputPort removeClientePorIdInputPort;
    private final ClienteMapper mapperWeb;

    public ClienteController(AtualizaClienteInputPort atualizaClienteInputPort,
                             BuscaClientePorIdOuCpfInputPort buscaClientePorIdOuCpfInputPort,
                             BuscaTodosClientesInputPort buscaTodosClientesInputPort,
                             CadastraClienteInputPort cadastraClienteInputPort,
                             RemoveClientePorIdInputPort removeClientePorIdInputPort,
                             ClienteMapper mapperWeb
    ) {
        this.atualizaClienteInputPort = atualizaClienteInputPort;
        this.buscaClientePorIdOuCpfInputPort = buscaClientePorIdOuCpfInputPort;
        this.buscaTodosClientesInputPort = buscaTodosClientesInputPort;
        this.cadastraClienteInputPort = cadastraClienteInputPort;
        this.removeClientePorIdInputPort = removeClientePorIdInputPort;
        this.mapperWeb = mapperWeb;
    }

    @Operation(summary = "Busca um Cliente pelo CPF")
    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteResponse> buscaPorCpf(@Parameter(example = "94187479015") @PathVariable String cpf) {
        ClienteResponse clienteResponse = mapperWeb.toClienteResponse(
                buscaClientePorIdOuCpfInputPort.buscar(cpf)
        );

        return ResponseEntity.ok(clienteResponse);
    }

    @Operation(summary = "Busca todos os Clientes")
    @GetMapping
    public ResponseEntity<List<ClienteResponse>> buscaTodos() {
        List<ClienteDTO> listaClientes = buscaTodosClientesInputPort.buscarTodos();
        List<ClienteResponse> clienteResponseList = mapperWeb.toClientesResponse(listaClientes);

        return ResponseEntity.ok(clienteResponseList);
    }

    @Operation(summary = "Cadastra um novo Cliente")
    @PostMapping
    public ResponseEntity<ClienteResponse> cadastra(@Valid @RequestBody ClienteRequest clienteRequest) {

        ClienteDTO clienteOut = cadastraClienteInputPort.cadastrar(clienteRequest.toClienteDTO());
        ClienteResponse clienteResponse = mapperWeb.toClienteResponse(clienteOut);

        var uri = getExpandedCurrentUri("/{id}", clienteResponse.getId());

        return ResponseEntity
                .created(uri)
                .body(clienteResponse);
    }

    @Operation(summary = "Atualiza Cliente pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualiza(@Valid @RequestBody ClienteRequest clienteRequest, @Parameter(example = "1") @PathVariable Long id) {
        ClienteDTO clienteAtualizado = atualizaClienteInputPort.atualizar(clienteRequest.toClienteDTO(), id);

        return ResponseEntity.ok(mapperWeb.toClienteResponse(clienteAtualizado));
    }

    @Operation(summary = "Remove um Cliente por Id")
    @DeleteMapping(value = "/{id}")
    public @ResponseBody ResponseEntity<ClienteResponse> removerPorId(@Parameter(example = "1") @PathVariable("id") Long id) {
        var clienteOut = removeClientePorIdInputPort.removerPorId(id);
        var produtoResponse = mapperWeb.toClienteResponse(clienteOut);

        return ResponseEntity.ok(produtoResponse);
    }

}
