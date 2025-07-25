package br.com.sfranca.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexResponseDTO {

    private List<GutendexBookDTO> results;

    public List<GutendexBookDTO> getResults() {
        return results;
    }

    public void setResults(List<GutendexBookDTO> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "GutendexResponseDTO{" +
                "results=" + results +
                '}';
    }
}
