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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RemoveClientePorIdUseCaseTest {

    @Mock
    private ClienteJpaRepository clienteJpaRepository;
    private ClienteMapper clienteMapper;
    private ClienteRepository clienteRepository;
    private RemoveClientePorIdUseCase removeClientePorIdUseCase;
    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        clienteMapper = new ClienteMapper(new EnderecoMapper());
        clienteRepository = new ClienteRepository(clienteJpaRepository, clienteMapper);
        removeClientePorIdUseCase = new RemoveClientePorIdUseCase(clienteRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    @DisplayName("Deve remover um cliente por id quando o id informado existir")
    void deveRemoverUmClientePorId_QuandoIdInformadoExistir() {
        //Arrange
        var clienteSalvo = ClienteHelper.criaCliente();
        when(clienteJpaRepository.findById(anyLong())).thenReturn(Optional.of(clienteSalvo));
        doNothing().when(clienteJpaRepository).delete(any(Cliente.class));

        //Act
        var clienteDTO = removeClientePorIdUseCase.removerPorId(1L);

        //Assert
        assertThat(clienteDTO).isNotNull();

        verify(clienteJpaRepository, times(1)).findById(anyLong());
        verify(clienteJpaRepository, times(1)).delete(any(Cliente.class));
    }

    @Test
    @DisplayName("Deve lançar EntityNotFoundException quando id do cliente informado não existir")
    void deveLancarEntityNotFoundException_QuandoIdClienteInformadoNaoExistir() {
        //Arrange
        Long id = 1L;
        String message = String.format("Cliente com id %s não encontrado", id);
        when(clienteJpaRepository.findById(anyLong())).thenReturn(Optional.empty());

        //Act
        //Assert
        assertThatThrownBy(() -> removeClientePorIdUseCase.removerPorId(id))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage(message);

        verify(clienteJpaRepository, times(1)).findById(anyLong());
    }
}
