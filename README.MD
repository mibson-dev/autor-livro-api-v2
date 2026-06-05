# Autor e Livro API v2 — API REST com Spring Security e JWT

Projeto desenvolvido com fins educacionais para fixação e evolução de conceitos de **Spring Boot**, construído como continuação direta do projeto [autor-livro-api](https://github.com/mibson-dev/autor-livro-api).

---

## Objetivo

Evoluir a API anterior adicionando autenticação com Spring Security e JWT, paginação nos endpoints de listagem e refinamentos gerais no código.

---

## O que foi adicionado neste projeto

### Spring Security com JWT
Implementação de autenticação stateless com JSON Web Token:
- Registro de usuário com senha criptografada via BCrypt
- Login com geração de token JWT
- Filtro (`JwtFilter`) que intercepta todas as requisições e valida o token
- Rotas públicas (`/auth/**`) e rotas protegidas (todos os demais endpoints)
- Chave secreta do JWT armazenada em variável de ambiente via `.env`

### Paginação
Substituição das listagens simples por paginação com Spring Data:
- `GET /autores` e `GET /livros` retornam `Page<DTO>` em vez de `List<DTO>`
- Parâmetros `page`, `size` e `sort` configuráveis via query string
- Valores padrão definidos com `@PageableDefault`
- Conversão via `.map()` em vez de loop `for`

### Java Records nos DTOs
Substituição das classes DTO por Records — eliminando construtores, getters e setters desnecessários.

---

## Tecnologias

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Spring Boot Starter Validation
- Lombok
- PostgreSQL
<<<<<<< HEAD
- jjwt 0.12.6
=======
>>>>>>> 7b245a1d323e49a262ef4f3a5ea5747d604027e3
- Maven

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

<<<<<<< HEAD
1. Faça `POST /auth/login` com email e senha
2. Copie o token retornado
3. Nas demais requisições vá em **Authorization → Bearer Token** e cole o token
=======
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
>>>>>>> 7b245a1d323e49a262ef4f3a5ea5747d604027e3
