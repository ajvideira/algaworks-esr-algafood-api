package br.com.ajvideira.algafood.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import br.com.ajvideira.algafood.domain.model.Cozinha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonRootName("cozinhas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CozinhasXmlWrapper {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("cozinha")
    private List<Cozinha> cozinhas;

}
