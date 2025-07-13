package br.com.sfranca.literalura.service;

import br.com.sfranca.literalura.client.GutendexHttpClient;
import br.com.sfranca.literalura.dto.GutendexBookDTO;
import br.com.sfranca.literalura.dto.GutendexResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final GutendexHttpClient gutendexHttpClient;

    public LivroService(GutendexHttpClient gutendexHttpClient) {
        this.gutendexHttpClient = gutendexHttpClient;
    }

    public List<GutendexBookDTO> obterLivrosMaisBaixados() {
        GutendexResponseDTO resposta = gutendexHttpClient.buscarLivros("https://gutendex.com/books/");
        return resposta.getResults();
    }
}
