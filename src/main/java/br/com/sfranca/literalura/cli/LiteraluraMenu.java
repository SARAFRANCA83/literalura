package br.com.sfranca.literalura.service;

import br.com.sfranca.literalura.dto.GutendexBookDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
public class LiteraluraMenu implements CommandLineRunner {

    private final LivroService livroService;
    private final EstatisticaService estatisticaService;

    // Ajuste o construtor para receber o EstatisticaService também:
    public LiteraluraMenu(LivroService livroService, EstatisticaService estatisticaService) {
        this.livroService = livroService;
        this.estatisticaService = estatisticaService;
    }


    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n===== MENU LITERALURA =====");
            System.out.println("1 - Listar livros mais baixados");
            System.out.println("2 - Buscar livro pelo título");
            System.out.println("3 - Listar livros registrados");
            System.out.println("4 - Listar autores registrados");
            System.out.println("5 - Listar autores vivos em um determinado ano");
            System.out.println("6 - Listar livros em um determinado idioma");
            System.out.println("7 - Contar livros por idioma");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 -> listarLivrosMaisBaixados();
                    case 2 -> buscarLivroPorTitulo(scanner);
                    case 3 -> listarLivrosRegistrados();
                    case 4 -> listarAutoresRegistrados();
                    case 5 -> listarAutoresVivosNoAno(scanner);
                    case 6 -> listarLivrosPorIdioma(scanner);
                    case 7 -> contarLivrosPorIdioma(scanner);
                    case 0 -> System.out.println("Saindo do programa...");
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private void contarLivrosPorIdioma(Scanner scanner) {
        System.out.print("Digite o código do idioma (ex: en, pt, fr): ");
        String idioma = scanner.nextLine().trim();

        long quantidade = estatisticaService.contarLivrosPorIdioma(idioma);

        System.out.println("Quantidade de livros no idioma '" + idioma + "': " + quantidade);
    }

    private void buscarLivroPorTitulo(Scanner scanner) {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();

        List<GutendexBookDTO> livros = livroService.buscarLivroPorTitulo(titulo);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado com esse título.");
        } else {
            livros.forEach(System.out::println);
        }
    }

    private void listarLivrosRegistrados() {
        List<GutendexBookDTO> livros = livroService.listarLivrosRegistrados();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado ainda.");
        } else {
            livros.forEach(System.out::println);
        }
    }

    private void listarLivrosMaisBaixados() {
        List<GutendexBookDTO> livros = livroService.obterLivrosMaisBaixados();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            livros.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        Set<String> autores = livroService.listarAutoresRegistrados();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado ainda.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarLivrosPorIdioma(Scanner scanner) {
        System.out.print("Digite o código do idioma (ex: en, pt, fr): ");
        String idioma = scanner.nextLine();

        List<GutendexBookDTO> livros = livroService.listarLivrosPorIdioma(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma informado.");
        } else {
            livros.forEach(System.out::println);
        }
    }

    private void listarAutoresVivosNoAno(Scanner scanner) {
        System.out.print("Digite o ano desejado: ");
        int ano = Integer.parseInt(scanner.nextLine());

        List<String> autores = livroService.listarAutoresVivosEm(ano);
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor vivo encontrado nesse ano.");
        } else {
            autores.forEach(System.out::println);
        }
    }
}
