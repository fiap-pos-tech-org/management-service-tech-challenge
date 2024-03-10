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
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clientes", description = "APIs para gerenciamento de Clientes")
@Validated
@RestController
@RequestMapping("/clientes")
public class ClienteController extends ControllerBase {


    private final AtualizaClienteInputPort atualizaClienteInputPort;
    private final BuscaClientePorIdOuCpfInputPort buscaClientePorIdOuCpfInputPort;
    private final BuscaTodosClientesInputPort buscaTodosClientesInputPort;
    private final CadastraClienteInputPort cadastraClienteInputPort;
    private final RemoveClienteInputPort removeClienteInputPort;
    private final ClienteMapper mapperWeb;

    public ClienteController(AtualizaClienteInputPort atualizaClienteInputPort,
                             BuscaClientePorIdOuCpfInputPort buscaClientePorIdOuCpfInputPort,
                             BuscaTodosClientesInputPort buscaTodosClientesInputPort,
                             CadastraClienteInputPort cadastraClienteInputPort,
                             RemoveClienteInputPort removeClienteInputPort,
                             ClienteMapper mapperWeb
    ) {
        this.atualizaClienteInputPort = atualizaClienteInputPort;
        this.buscaClientePorIdOuCpfInputPort = buscaClientePorIdOuCpfInputPort;
        this.buscaTodosClientesInputPort = buscaTodosClientesInputPort;
        this.cadastraClienteInputPort = cadastraClienteInputPort;
        this.removeClienteInputPort = removeClienteInputPort;
        this.mapperWeb = mapperWeb;
    }

    @Operation(summary = "Busca um Cliente pelo CPF")
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteResponse> buscaPorCpf(@Parameter(example = "94187479015")
                                                       @Pattern(regexp = "^\\d{11}$", message = "CPF informado inválido")
                                                       @PathVariable String cpf) {
        ClienteResponse clienteResponse = mapperWeb.toClienteResponse(
                buscaClientePorIdOuCpfInputPort.buscar(cpf)
        );

        return ResponseEntity.ok(clienteResponse);
    }

    @Operation(summary = "Busca um Cliente por Id")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscaPorId(@Parameter(example = "1")
                                                      @PathVariable("id")
                                                      @Pattern(regexp = "^\\d*$", message = "O id do cliente deve conter apenas números") String id) {
        var clienteDTO = buscaClientePorIdOuCpfInputPort.buscar(Long.parseLong(id));
        var clienteResponse = mapperWeb.toClienteResponse(clienteDTO);
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
    public ResponseEntity<ClienteResponse> atualiza(@Valid @RequestBody ClienteRequest clienteRequest,
                                                    @Parameter(example = "1") @PathVariable Long id) {
        ClienteDTO clienteAtualizado = atualizaClienteInputPort.atualizar(clienteRequest.toClienteDTO(), id);

        return ResponseEntity.ok(mapperWeb.toClienteResponse(clienteAtualizado));
    }

    @Operation(summary = "Remove Cliente por CPF")
    @DeleteMapping(value = "/cpf/{cpf}")
    public ResponseEntity<ClienteResponse> remover(@Parameter(example = "94187479015")
                                                   @Pattern(regexp = "^\\d{11}$", message = "CPF informado inválido")
                                                   @PathVariable("cpf") String cpf) {
        var clienteOut = removeClienteInputPort.remover(cpf);
        var produtoResponse = mapperWeb.toClienteResponse(clienteOut);

        return ResponseEntity.ok(produtoResponse);
    }

}
