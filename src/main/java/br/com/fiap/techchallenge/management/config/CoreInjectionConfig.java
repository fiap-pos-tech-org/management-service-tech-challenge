package br.com.fiap.techchallenge.management.config;

import br.com.fiap.techchallenge.management.core.ports.in.cliente.*;
import br.com.fiap.techchallenge.management.core.ports.out.cliente.*;
import br.com.fiap.techchallenge.management.core.usecases.cliente.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreInjectionConfig {

    @Bean
    AtualizaClienteInputPort atualizaCliente(AtualizaClienteOutputPort atualizaClienteOutputPort) {
        return new AtualizaClienteUseCase(atualizaClienteOutputPort);
    }

    @Bean
    BuscaClientePorIdOuCpfInputPort buscaClientePorCpf(BuscaClienteOutputPort buscaClienteOutputPort) {
        return new BuscaClientePorIdOuCpfIdOuCpfUseCase(buscaClienteOutputPort);
    }

    @Bean
    BuscaTodosClientesInputPort buscaTodosClientes(BuscaTodosClientesOutputPort buscaTodosClientesOutputPort) {
        return new BuscaTodosClientesUseCase(buscaTodosClientesOutputPort);
    }

    @Bean
    CadastraClienteInputPort cadastraCliente(CadastraClienteOutputPort cadastraClienteOutputPort) {
        return new CadastraClienteUseCase(cadastraClienteOutputPort);
    }

    @Bean
    RemoveClienteInputPort removerCliente(RemoveClienteOutputPort removeClienteOutputPort) {
        return new RemoveClienteUseCase(removeClienteOutputPort);
    }

}
