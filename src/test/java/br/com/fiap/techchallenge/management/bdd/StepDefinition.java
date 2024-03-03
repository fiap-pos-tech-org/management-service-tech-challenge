package br.com.fiap.techchallenge.management.bdd;

import br.com.fiap.techchallenge.management.adapters.web.models.responses.ClienteResponse;
import br.com.fiap.techchallenge.management.utils.ClienteHelper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class StepDefinition {

    private Response response;
    private ClienteResponse clienteResponse;

    @Quando("preencher todos os dados para cadastro do cliente")
    public ClienteResponse preencherTodosDadosParaCadastrarCliente() {
        var clienteRequest = ClienteHelper.criaClienteRequestAleatorio();
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(clienteRequest)
                .when()
                .post("/clientes");
        return response.then()
                .extract()
                .as(ClienteResponse.class);
    }

    @Então("o cliente deve ser criado com sucesso")
    public void clienteDeveSerCriadoComSucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Então("deve exibir o cliente cadastrado")
    public void deveExibirClienteCadastrado() {
        response.then()
                .body(matchesJsonSchemaInClasspath("./schemas/ClienteResponseSchema.json"));
    }

    @Dado("que um cliente já está cadastrado")
    public void clienteJaCadastrado() {
        clienteResponse = preencherTodosDadosParaCadastrarCliente();
    }

    @Quando("realizar a requisição para alterar o cliente")
    public void realizarRequisicaoParaAlterarCliente() {
        clienteResponse.setNome("Cliente Teste Alterado");
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(clienteResponse)
                .when()
                .put("/clientes/{id}", clienteResponse.getId());
    }

    @Então("o cliente deve ser alterado com sucesso")
    public void clienteDeveSerAlteradoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Então("deve exibir o cliente alterado")
    public void deveExibirClienteAlterado() {
        response.then()
                .body(matchesJsonSchemaInClasspath("./schemas/ClienteResponseSchema.json"))
                .body("nome", equalTo(clienteResponse.getNome()));
    }

    @Quando("requisitar a lista de todos os clientes")
    public void requisitarListaTodosClientes() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/clientes");
    }

    @Então("os clientes devem ser exibidos com sucesso")
    public void clientesDevemSerExibidosComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(greaterThanOrEqualTo(1)))
                .body("$", everyItem(anything()));
    }

    @Quando("realizar a busca do cliente")
    public void realizarBuscaCliente() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/clientes/{cpf}", clienteResponse.getCpf());
    }

    @Então("o cliente deve ser exibido com sucesso")
    public void clienteDeveSerExibidoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ClienteResponseSchema.json"));
    }

    @Quando("realizar a requisição para remover o cliente")
    public void realizarRequisicaoParaRemoverCliente() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(clienteResponse)
                .when()
                .delete("/clientes/{cpf}", clienteResponse.getCpf());
    }

    @Então("o cliente deve ser removido com sucesso")
    public void clienteDeveSerRemovidoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ClienteResponseSchema.json"));
    }

}
