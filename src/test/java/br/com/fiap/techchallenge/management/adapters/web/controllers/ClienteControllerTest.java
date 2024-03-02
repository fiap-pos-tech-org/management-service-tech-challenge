package br.com.fiap.techchallenge.management.adapters.web.controllers;

import br.com.fiap.techchallenge.management.adapters.web.ClienteController;
import br.com.fiap.techchallenge.management.adapters.web.handlers.ExceptionsHandler;
import br.com.fiap.techchallenge.management.adapters.web.mappers.ClienteMapper;
import br.com.fiap.techchallenge.management.adapters.web.models.requests.ClienteRequest;
import br.com.fiap.techchallenge.management.adapters.web.models.responses.ClienteResponse;
import br.com.fiap.techchallenge.management.core.domain.exceptions.EntityNotFoundException;
import br.com.fiap.techchallenge.management.core.dtos.ClienteDTO;
import br.com.fiap.techchallenge.management.core.dtos.ProdutoDTO;
import br.com.fiap.techchallenge.management.core.ports.in.cliente.*;
import br.com.fiap.techchallenge.management.utils.ClienteHelper;
import br.com.fiap.techchallenge.management.utils.ObjectParaJsonMapper;
import br.com.fiap.techchallenge.management.utils.ProdutoHelper;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClienteControllerTest {

    private MockMvc mockMvc;
    @Mock
    private AtualizaClienteInputPort atualizaClienteInputPort;
    @Mock
    private BuscaClientePorIdOuCpfInputPort buscaClientePorIdOuCpfInputPort;
    @Mock
    private BuscaTodosClientesInputPort buscaTodosClientesInputPort;
    @Mock
    private CadastraClienteInputPort cadastraClienteInputPort;
    @Mock
    private RemoveClientePorIdInputPort removeClientePorIdInputPort;
    @Mock
    private ClienteMapper mapperWeb;
    private ClienteDTO clienteDTO;
    private ClienteResponse clienteResponse;
    private ClienteRequest clienteRequest;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        ClienteController clienteController = new ClienteController(
                atualizaClienteInputPort,
                buscaClientePorIdOuCpfInputPort,
                buscaTodosClientesInputPort,
                cadastraClienteInputPort,
                removeClientePorIdInputPort,
                mapperWeb
        );
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).setControllerAdvice(new ExceptionsHandler()).build();

        clienteDTO = ClienteHelper.criaClienteDTO();
        clienteResponse = ClienteHelper.criaClienteResponse();
        clienteRequest = ClienteHelper.criaClienteRequest();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Nested
    @DisplayName("Busca por CPF")
    class BuscaPorCpf {

        @Test
        @DisplayName("Deve retornar um cliente quando informado um cpf válido")
        void deveRetornarUmCliente_QuandoInformadoUmCpfValido() throws Exception {
            //Arrange
            when(buscaClientePorIdOuCpfInputPort.buscar(anyString())).thenReturn(clienteDTO);
            when(mapperWeb.toClienteResponse(any(ClienteDTO.class))).thenReturn(clienteResponse);

            //Act
            //Assert
            mockMvc.perform(get("/clientes/{cpf}", "56312729036"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.cpf").value("56312729036"))
                    .andExpect(jsonPath("$.email").value("cliente1@email.com"));
            verify(buscaClientePorIdOuCpfInputPort, times(1)).buscar(anyString());
        }

        @Test
        @DisplayName("Deve retornar not found quando informado um cpf inválido")
        void deveRetornarNotFound_QuandoInformadoUmCpfInvalido() throws Exception {
            //Arrange
            when(buscaClientePorIdOuCpfInputPort.buscar(anyString())).thenThrow(EntityNotFoundException.class);

            //Act
            //Assert
            mockMvc.perform(get("/clientes/{cpf}", "10000000007"))
                    .andExpect(status().isNotFound());
            verify(buscaClientePorIdOuCpfInputPort, times(1)).buscar(anyString());
        }
    }

    @Nested
    @DisplayName("Busca todos os clientes")
    class BuscaTodosClientes {

        @Test
        @DisplayName("Deve retornar uma lista de clientes")
        void deveRetornarUmaListaDeClientes() throws Exception {
            //Arrange
            List<ClienteDTO> listaClientes = List.of(clienteDTO);
            List<ClienteResponse> listaClientesResponse = List.of(clienteResponse);
            when(buscaTodosClientesInputPort.buscarTodos()).thenReturn(listaClientes);
            when(mapperWeb.toClientesResponse(anyList())).thenReturn(listaClientesResponse);

            //Act
            //Assert
            mockMvc.perform(get("/clientes"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].cpf").value("56312729036"))
                    .andExpect(jsonPath("$[0].email").value("cliente1@email.com"));

        }
    }

    @Nested
    @DisplayName("Cadastra um cliente")
    class CadastraCliente {

        @Test
        @DisplayName("Deve cadastrar um cliente quando os dados forem informados corretamente")
        void deveCadastrarUmCliente_QuandoOsDadosForemInformadosCorretamente() throws Exception {
            //Arrange
            when(cadastraClienteInputPort.cadastrar(any(ClienteDTO.class))).thenReturn(clienteDTO);
            when(mapperWeb.toClienteResponse(clienteDTO)).thenReturn(clienteResponse);

            //Act
            //Assert
            mockMvc.perform(post("/clientes")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(ObjectParaJsonMapper.converte(clienteRequest)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.cpf").value("56312729036"))
                    .andExpect(jsonPath("$.email").value("cliente1@email.com"));
            verify(cadastraClienteInputPort, times(1)).cadastrar(any(ClienteDTO.class));
        }
    }

    @Nested
    @DisplayName("Atualiza um cliente")
    class AtualizaCliente {

        @Test
        @DisplayName("Deve atualizar dados do cliente quando dados corretamente informados")
        void deveAtualizarDadosDoCliente_QuandoDadosCorretamenteInformados() throws Exception {
            //Arrange
            Long id = 1L;
            when(atualizaClienteInputPort.atualizar(any(ClienteDTO.class), anyLong())).thenReturn(clienteDTO);
            when(mapperWeb.toClienteResponse(clienteDTO)).thenReturn(clienteResponse);

            //Act
            //Assert
            mockMvc.perform(put("/clientes/{id}", 1)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(ObjectParaJsonMapper.converte(clienteRequest)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.cpf").value("56312729036"))
                    .andExpect(jsonPath("$.email").value("cliente1@email.com"));
        }
    }

    @Nested
    @DisplayName("Remove um cliente")
    class RemoveCliente {

        @Test
        @DisplayName("Deve remover um cliente por id quando o id informado existir")
        void deveRemoverUmClientePorId_QuandoIdInformadoExistir() throws Exception {
            //Arrange
            when(removeClientePorIdInputPort.removerPorId(anyLong())).thenReturn(ClienteHelper.criaClienteDTO());
            when(mapperWeb.toClienteResponse(any(ClienteDTO.class))).thenReturn(ClienteHelper.criaClienteResponse());

            //Act
            //Assert
            mockMvc.perform(delete("/clientes/{id}", 1L)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("$.id").isNotEmpty(),
                            jsonPath("$.id").isNumber(),
                            jsonPath("$.nome").isNotEmpty(),
                            jsonPath("$.nome").isString(),
                            jsonPath("$.cpf").isNotEmpty(),
                            jsonPath("$.cpf").isString(),
                            jsonPath("$.email").isNotEmpty(),
                            jsonPath("$.email").isString(),
                            jsonPath("$.telefone").isNotEmpty(),
                            jsonPath("$.telefone").isString(),
                            jsonPath("$.endereco.id").isNotEmpty(),
                            jsonPath("$.endereco.id").isNumber(),
                            jsonPath("$.endereco.logradouro").isNotEmpty(),
                            jsonPath("$.endereco.logradouro").isString(),
                            jsonPath("$.endereco.rua").isNotEmpty(),
                            jsonPath("$.endereco.rua").isString(),
                            jsonPath("$.endereco.numero").isNotEmpty(),
                            jsonPath("$.endereco.numero").isNumber(),
                            jsonPath("$.endereco.bairro").isNotEmpty(),
                            jsonPath("$.endereco.bairro").isString(),
                            jsonPath("$.endereco.cidade").isNotEmpty(),
                            jsonPath("$.endereco.cidade").isString(),
                            jsonPath("$.endereco.estado").isNotEmpty(),
                            jsonPath("$.endereco.estado").isString()
                    );
        }

        @Test
        @DisplayName("Deve retornar Not Found quando id do cliente não existir")
        void deveRetornarNotFound_QuandoIdClienteNaoExistir() throws Exception {
            //Arrange
            Long id = 1L;
            String mensagem = String.format("Cliente com id %s não encontrado", id);
            when(removeClientePorIdInputPort.removerPorId(anyLong())).thenThrow(new EntityNotFoundException(mensagem));

            //Act
            //Assert
            mockMvc.perform(delete("/clientes/{id}", id)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.message").value(mensagem));

        }
    }

}
