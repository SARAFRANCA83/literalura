package br.com.sfranca.literalura.client;

import br.com.sfranca.literalura.dto.GutendexResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Component
public class GutendexHttpClient {

    private static final Logger logger = LoggerFactory.getLogger(GutendexHttpClient.class);

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public GutendexHttpClient() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.objectMapper = new ObjectMapper();
    }

    public GutendexResponseDTO buscarLivros(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Accept", "application/json")
                    .timeout(Duration.ofSeconds(10))
                    .GET()
                    .build();

            logger.info("Enviando requisição GET para URL: {}", url);

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            logger.info("Recebido status HTTP: {}", statusCode);

            if (statusCode != 200) {
                throw new IOException("Erro inesperado na resposta HTTP: código " + statusCode);
            }

            String body = response.body();

            logger.debug("Corpo da resposta: {}", body);

            return objectMapper.readValue(body, GutendexResponseDTO.class);

        } catch (IOException | InterruptedException e) {
            logger.error("Erro ao buscar livros na API: {}", e.getMessage());
            throw new RuntimeException("Falha ao consultar API de livros", e);
        }
    }
}
