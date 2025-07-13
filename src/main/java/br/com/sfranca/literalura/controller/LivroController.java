package br.com.sfranca.literalura.dto;

import br.com.sfranca.literalura.service.LivroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public List<GutendexBookDTO> listarLivros() {
        return livroService.obterLivrosMaisBaixados();
    }
}


