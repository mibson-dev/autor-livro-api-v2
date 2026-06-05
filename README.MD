# Autores e Livros — API REST com Spring Security e JWT

API REST de gerenciamento de autores e livros desenvolvida para praticar conceitos de Spring Boot, com foco em autenticação via Spring Security e JWT, paginação e boas práticas gerais.

---

## Tecnologias

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Spring Boot Starter Validation
- Lombok
- PostgreSQL
- jjwt 0.12.6
- Maven

---

## Funcionalidades

### Autenticação com JWT
- Registro de usuário com senha criptografada via BCrypt
- Login com geração de token JWT
- Filtro de autenticação em todas as requisições
- Chave secreta protegida via variável de ambiente

### Boas práticas
- Arquitetura em camadas — Controller, Service, Repository
- DTOs de entrada e saída com Java Records
- Lombok nas entidades
- Bean Validation nos RequestDTOs com @Valid
- Tratamento global de exceções com @ControllerAdvice e ErroResponse estruturado
- Paginação com Page e @PageableDefault

---

## Estrutura do projeto

```
src/main/java/
├── config/           → AppConfig (PasswordEncoder, AuthenticationManager)
├── controller/       → endpoints HTTP
├── security/         → JwtFilter, JwtService, SecurityConfig
├── services/         → regras de negócio
├── repositories/     → acesso ao banco
├── model/            → entidades JPA
├── DTO/              → objetos de transferência de dados
└── exceptions/       → tratamento global de erros
```

---

## Como executar

1. Clone o repositório
2. Crie um banco PostgreSQL
3. Crie um arquivo `.env` na raiz com base no `.env.example`:
```
DB_URL=jdbc:postgresql://localhost:5432/nome_do_banco
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
JWT_SECRET=sua-chave-secreta-minimo-32-caracteres
```
4. Execute a aplicação pelo IntelliJ ou via Maven:
```bash
./mvnw spring-boot:run
```

---

## Endpoints

### Autenticação (público)
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/auth/registrar` | Registra um novo usuário |
| POST | `/auth/login` | Realiza login e retorna o token JWT |

### Autores (requer token)
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/autores` | Lista autores com paginação |
| GET | `/autores/{id}` | Busca autor por ID |
| POST | `/autores` | Cadastra um novo autor |
| DELETE | `/autores/{id}` | Remove um autor |

### Livros (requer token)
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/livros` | Lista livros com paginação |
| GET | `/livros/{id}` | Busca livro por ID |
| POST | `/livros` | Cadastra um novo livro vinculado a um autor |
| DELETE | `/livros/{id}` | Remove um livro |

---

## Como usar o token no Postman

1. Faça `POST /auth/login` com email e senha
2. Copie o token retornado
3. Nas demais requisições vá em **Authorization → Bearer Token** e cole o token
