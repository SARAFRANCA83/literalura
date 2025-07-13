package br.com.sfranca.literalura.service;

import br.com.sfranca.literalura.client.GutendexHttpClient;
import br.com.sfranca.literalura.dto.GutendexAuthorDTO;
import br.com.sfranca.literalura.dto.GutendexBookDTO;
import br.com.sfranca.literalura.dto.GutendexResponseDTO;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class LivroService {

    private final GutendexHttpClient gutendexHttpClient;

    // Armazenamento em memória
    private final List<GutendexBookDTO> livrosRegistrados = new ArrayList<>();
    private final Set<String> autoresRegistrados = new HashSet<>();

    public LivroService(GutendexHttpClient gutendexHttpClient) {
        this.gutendexHttpClient = gutendexHttpClient;
    }

    public List<GutendexBookDTO> obterLivrosMaisBaixados() {
        GutendexResponseDTO resposta = gutendexHttpClient.buscarLivros("https://gutendex.com/books/");
        registrarLivros(resposta.getResults());
        return resposta.getResults();
    }

    public List<GutendexBookDTO> buscarLivroPorTitulo(String titulo) {
        String tituloCodificado = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
        GutendexResponseDTO resposta = gutendexHttpClient.buscarLivros("https://gutendex.com/books/?search=" + tituloCodificado);
        registrarLivros(resposta.getResults());
        return resposta.getResults();
    }

    public List<GutendexBookDTO> listarLivrosRegistrados() {
        return livrosRegistrados;
    }

    public Set<String> listarAutoresRegistrados() {
        return autoresRegistrados;
    }

    public List<GutendexBookDTO> listarLivrosPorIdioma(String idioma) {
        return livrosRegistrados.stream()
                .filter(livro -> livro.getLanguages() != null && livro.getLanguages().contains(idioma))
                .toList();
    }

    public List<String> listarAutoresVivosEm(int ano) {
        return livrosRegistrados.stream()
                .flatMap(livro -> livro.getAuthors().stream())
                .filter(autor -> autor.getBirthYear() <= ano && autor.getDeathYear() >= ano)
                .map(GutendexAuthorDTO::getName)
                .distinct()
                .toList();
    }

    // Atualiza as listas em memória
    private void registrarLivros(List<GutendexBookDTO> livros) {
        for (GutendexBookDTO livro : livros) {
            if (!livrosRegistrados.contains(livro)) {
                livrosRegistrados.add(livro);
            }
            if (livro.getAuthors() != null) {
                livro.getAuthors().forEach(autor -> autoresRegistrados.add(autor.getName()));
            }
        }
    }
}
