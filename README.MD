# Autor e Livro — API REST (Projeto de Treinamento)

Projeto desenvolvido com fins educacionais para fixação e evolução de conceitos de **Spring Boot**, construído como continuação de estudos iniciados no projeto [Diretores-e-Filmes](https://github.com/mibson-dev/Diretores-e-Filmes).

---

## Objetivo

Praticar e consolidar conceitos de desenvolvimento de APIs REST com Java e Spring Boot, com foco em redução de boilerplate com Lombok e validação de dados de entrada com Bean Validation.

---

## O que foi aprendido neste projeto

Em comparação com o projeto anterior, este projeto introduziu e reforçou os seguintes conceitos:

### Lombok
Eliminação de código boilerplate com as anotações do Lombok:
- `@Getter` e `@Setter` — geração automática de getters e setters
- `@NoArgsConstructor` — construtor vazio
- `@AllArgsConstructor` — construtor com todos os atributos

Nas entidades JPA foi evitado o uso de `@Data` para não causar conflitos com o Hibernate.

### Bean Validation
Validação dos dados de entrada diretamente no `RequestDTO` com anotações como:
- `@NotBlank` — para Strings obrigatórias
- `@NotNull` — para objetos e datas obrigatórios
- `@Positive` — para números que devem ser positivos

Ativação da validação no Controller com `@Valid` e tratamento do erro de validação no `GlobalExceptionHandler` com `MethodArgumentNotValidException`.

---

## Tecnologias

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Boot Starter Validation
- Lombok
- PostgreSQL
- Maven

---

## Estrutura do projeto

```
src/main/java/
├── controller/       → endpoints HTTP
├── services/         → regras de negócio
├── repositories/     → acesso ao banco
├── model/            → entidades JPA
├── DTO/              → objetos de transferência de dados
└── exceptions/       → tratamento global de erros
```

---

## Como executar

1. Clone o repositório
2. Execute a aplicação pelo IntelliJ ou via Maven:
```bash
./mvnw spring-boot:run
```
O banco H2 é criado automaticamente em memória — nenhuma configuração adicional necessária.

Console H2 disponível em `http://localhost:8080/h2-console` com JDBC URL `jdbc:h2:mem:testdb`.

---

## Endpoints

### Autores
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/autores` | Lista todos os autores |
| GET | `/autores/{id}` | Busca autor por ID |
| POST | `/autores` | Cadastra um novo autor |
| DELETE | `/autores/{id}` | Remove um autor |

### Livros
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/livros` | Lista todos os livros |
| GET | `/livros/{id}` | Busca livro por ID |
| POST | `/livros` | Cadastra um novo livro vinculado a um autor |
| DELETE | `/livros/{id}` | Remove um livro |

---

## Exemplos de requisição

### Cadastrar autor
```json
POST /autores
{
    "nome": "Machado de Assis",
    "nacionalidade": "Brasileira"
}
```

### Cadastrar livro
```json
POST /livros
{
    "titulo": "Dom Casmurro",
    "anoPublicacao": 1899,
    "genero": "Romance",
    "autor": {
        "id": 1
    }
}
```
