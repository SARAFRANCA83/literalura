# 📚 Literalura

Literalura é uma aplicação Java com Spring Boot que consome dados da [API Gutendex](https://gutendex.com/) (Project Gutenberg) e permite registrar, consultar e exibir livros e autores clássicos. A aplicação inclui uma interface em linha de comando (CLI) e um endpoint REST, oferecendo tanto interação direta quanto integração externa.

---

## 🚀 Funcionalidades

- 🔍 Buscar livros mais baixados da API Gutendex  
- ✍️ Registrar livros e autores no banco local  
- 📖 Buscar livro por título  
- 📚 Listar livros e autores já registrados  
- 🌍 Filtrar livros por idioma  
- 🎂 Consultar autores vivos em determinado ano  
- 📊 Contar livros por idioma via estatísticas  
- 📡 Endpoint REST para consultas externas  

---

## 🧱 Tecnologias Utilizadas

| Tecnologia        | Função                                 |
|------------------|----------------------------------------|
| Java 17+         | Linguagem principal                    |
| Spring Boot      | Framework backend                      |
| Spring Data JPA  | Persistência com Hibernate             |
| PostgreSQL / H2  | Banco de dados (configurável)          |
| Jackson          | Serialização e desserialização JSON    |
| Maven            | Gerenciador de dependências            |
| SLF4J + Logback  | Logging estruturado                    |
| Gutendex API     | Fonte de dados externos sobre livros   |

---

## 🧩 Arquitetura de Camadas

- `LiteraluraApplication` → ponto de entrada da aplicação Spring Boot  
- `LiteraluraMenu` → interface CLI para usuários  
- `GutendexHttpClient` → realiza chamadas à API externa  
- `LivroService` e `EstatisticaService` → regras de negócio  
- `LivroController` → endpoint REST exposto em `/livros`  
- `DTOs` → estrutura para mapear e tratar dados da API  
- `Entidades` → mapeamento JPA (`Autor`, `Livro`) com relacionamento bidirecional  

---

## 💻 Execução Local

### 1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/literalura.git
cd literalura

**2. Configure o banco no arquivo application.properties:**

Para H2 (em memória):
spring.datasource.url=jdbc:h2:mem:literalura
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update

Para PostgreSQL (local):

spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

3. Execute a aplicação:

./mvnw spring-boot:run

4. Use o menu no terminal:

===== MENU LITERALURA =====
1 - Listar livros mais baixados
2 - Buscar livro pelo título
3 - Listar livros registrados
...

🌐 API REST
Método	Endpoint	Descrição
GET	/livros	Retorna a lista dos livros mais populares da API

📦 Exemplo de resposta JSON

[
  {
    "title": "Frankenstein",
    "downloadCount": 82500,
    "languages": ["en"],
    "authors": [
      {
        "name": "Mary Shelley",
        "birthYear": 1797,
        "deathYear": 1851
      }
    ]
  }
]

📊 Estatísticas disponíveis
Número total de autores registrados

Quantidade de livros por idioma

Listagem de autores vivos em um ano específico

🛠️ Melhorias Futuras
Paginação de resultados

Exportação para JSON/CSV

Dashboard com Spring MVC ou JavaFX

Autenticação de usuários

Busca avançada com múltiplos critérios

Suporte à internacionalização da interface

👩‍💻 Autoria
Desenvolvido por Sara França — entusiasta da literatura e tecnologia — com foco em arquitetura limpa, integração com APIs públicas e domínio da stack Java Spring Boot.
