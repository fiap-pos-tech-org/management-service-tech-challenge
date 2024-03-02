package br.com.fiap.techchallenge.management.config;

import br.com.fiap.techchallenge.management.core.ports.in.cliente.AtualizaClienteInputPort;
import br.com.fiap.techchallenge.management.core.ports.in.cliente.BuscaClientePorIdOuCpfInputPort;
import br.com.fiap.techchallenge.management.core.ports.in.cliente.BuscaTodosClientesInputPort;
import br.com.fiap.techchallenge.management.core.ports.in.cliente.CadastraClienteInputPort;
import br.com.fiap.techchallenge.management.core.ports.in.produto.*;
import br.com.fiap.techchallenge.management.core.ports.out.cliente.AtualizaClienteOutputPort;
import br.com.fiap.techchallenge.management.core.ports.out.cliente.BuscaClienteOutputPort;
import br.com.fiap.techchallenge.management.core.ports.out.cliente.BuscaTodosClientesOutputPort;
import br.com.fiap.techchallenge.management.core.ports.out.cliente.CadastraClienteOutputPort;
import br.com.fiap.techchallenge.management.core.ports.out.produto.*;
import br.com.fiap.techchallenge.management.core.usecases.cliente.AtualizaClienteUseCase;
import br.com.fiap.techchallenge.management.core.usecases.cliente.BuscaClientePorIdOuCpfIdOuCpfUseCase;
import br.com.fiap.techchallenge.management.core.usecases.cliente.BuscaTodosClientesUseCase;
import br.com.fiap.techchallenge.management.core.usecases.cliente.CadastraClienteUseCase;
import br.com.fiap.techchallenge.management.core.usecases.produto.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreInjectionConfig {

    @Bean
    CriaProdutoInputPort criarProduto(CriaProdutoOutputPort criaProdutoOutputPort) {
        return new CriaProdutoUseCase(criaProdutoOutputPort);
    }

    @Bean
    AtualizaImagemProdutoInputPort criarImagemProduto(AtualizaImagemProdutoOutputPort atualizaImagemProdutoOutputPort) {
        return new AtualizaImagemProdutoUseCase(atualizaImagemProdutoOutputPort);
    }

    @Bean
    EditaProdutoInputPort editarProduto(EditaProdutoOutputPort editaProdutoOutputPort) {
        return new EditaProdutoUseCase(editaProdutoOutputPort);
    }

    @Bean
    RemoveProdutoInputPort removerProduto(RemoveProdutoOutputPort removeProdutoOutputPort) {
        return new RemoveProdutoUseCase(removeProdutoOutputPort);
    }

    @Bean
    BuscaProdutoPorIdInputPort buscarProdutoPorId(BuscaProdutoPorIdOutputPort buscaProdutoPorIdOutputPort) {
        return new BuscaProdutoPorIdUseCase(buscaProdutoPorIdOutputPort);
    }

    @Bean
    BuscaTodosProdutosInputPort buscarTodos(BuscaTodosProdutosOutputPort buscaProdutoPorIdOutputPort) {
        return new BuscaTodosProdutosUseCase(buscaProdutoPorIdOutputPort);
    }

    @Bean
    BuscaProdutoPorCategoriaInputPort buscarPorCategoria(BuscaProdutoPorCategoriaOutputPort buscaProdutoPorIdOutputPort) {
        return new BuscaProdutoPorCategoriaUseCase(buscaProdutoPorIdOutputPort);
    }

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

}
