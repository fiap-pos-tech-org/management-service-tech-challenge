package br.com.fiap.techchallenge.management.adapters.web.mappers;

import br.com.fiap.techchallenge.management.adapters.web.models.responses.EnderecoResponse;
import br.com.fiap.techchallenge.management.core.dtos.EnderecoDTO;
import org.springframework.stereotype.Component;

@Component("EnderecoMapperWeb")
public class EnderecoMapper {

    public EnderecoResponse toEnderecoResponse(EnderecoDTO endereco) {
        return new EnderecoResponse(endereco.id(), endereco.logradouro(), endereco.rua(), endereco.numero(),
                endereco.bairro(), endereco.cidade(), endereco.estado());
    }

}