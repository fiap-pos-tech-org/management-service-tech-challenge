package br.com.fiap.techchallenge.management.core.usecases.cliente;

import br.com.fiap.techchallenge.management.adapters.repository.ClienteRepository;
import br.com.fiap.techchallenge.management.adapters.repository.jpa.ClienteJpaRepository;
import br.com.fiap.techchallenge.management.adapters.repository.mappers.ClienteMapper;
import br.com.fiap.techchallenge.management.adapters.repository.mappers.EnderecoMapper;
import br.com.fiap.techchallenge.management.adapters.repository.models.Cliente;
import br.com.fiap.techchallenge.management.core.domain.exceptions.EntityNotFoundException;
import br.com.fiap.techchallenge.management.utils.ClienteHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RemoveClienteUseCaseTest {

    @Mock
    private ClienteJpaRepository clienteJpaRepository;
    private ClienteMapper clienteMapper;
    private ClienteRepository clienteRepository;
    private RemoveClienteUseCase removeClienteUseCase;
    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        clienteMapper = new ClienteMapper(new EnderecoMapper());
        clienteRepository = new ClienteRepository(clienteJpaRepository, clienteMapper);
        removeClienteUseCase = new RemoveClienteUseCase(clienteRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    @DisplayName("Deve remover um cliente por CPF quando o id informado existir")
    void deveRemoverUmClientePorCpf_QuandoCpfInformadoExistir() {
        //Arrange
        var clienteSalvo = ClienteHelper.criaCliente();
        when(clienteJpaRepository.findByCpf(anyString())).thenReturn(Optional.of(clienteSalvo));
        doNothing().when(clienteJpaRepository).delete(any(Cliente.class));

        //Act
        var clienteDTO = removeClienteUseCase.remover("94187479015");

        //Assert
        assertThat(clienteDTO).isNotNull();

        verify(clienteJpaRepository).findByCpf(anyString());
        verify(clienteJpaRepository).delete(any(Cliente.class));
    }

    @Test
    @DisplayName("Deve lançar EntityNotFoundException quando CPF do cliente informado não existir")
    void deveLancarEntityNotFoundException_QuandoCpfClienteInformadoNaoExistir() {
        //Arrange
        var cpf = "94187479015";
        String message = String.format("Cliente com CPF %s não encontrado", cpf);
        when(clienteJpaRepository.findByCpf(anyString())).thenReturn(Optional.empty());

        //Act
        //Assert
        assertThatThrownBy(() -> removeClienteUseCase.remover(cpf))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage(message);

        verify(clienteJpaRepository).findByCpf(anyString());
    }
}
