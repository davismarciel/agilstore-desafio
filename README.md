
# Agile Store Manager

Bem-vindo ao **Agile Store Manager**, uma aplicação Spring Boot projetada para gerenciamento ágil de estoque e lojas.

## Tecnologias Utilizadas

A aplicação utiliza as seguintes tecnologias:

- **Java 17**: Linguagem principal para desenvolvimento.
- **Spring Boot 3.4.1**: Framework para desenvolvimento rápido e produtivo de aplicações Java.
- **Spring Data JPA**: Integração com bancos de dados relacionais usando o padrão JPA.
- **H2 Database**: Banco de dados em memória para testes e desenvolvimento.
- **PostgreSQL**: Banco de dados relacional para o ambiente de produção.
- **Spring Boot Validation**: Validação de dados da aplicação.
- **Spring Boot DevTools**: Ferramentas de desenvolvimento, como reinicialização automática.
- **Spring Boot Starter Web**: Para desenvolvimento de APIs REST.
- **Spring Boot Starter Test**: Para testes unitários e de integração.

## Requisitos

- **Java 17 ou superior**: Certifique-se de que está utilizando a versão adequada.
- **Maven**: Para gerenciar dependências e compilar o projeto.
## Rodando o projeto

Entre no diretório do projeto

```bash
  git clone https://github.com/davismarciel/agilstore-desafio
```

Entre no diretório do projeto

```bash
  cd agilstore-desafio
```

Crie o Arquivo .env

```bash
  cp .env.example .env
```

Atualize as variaveis .env

Exemplo para executar em produção:
```bash
SPRING_PROFILES_ACTIVE=prod
POSTGRES_DB=agile-db
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
```

Suba os containers do projeto

```bash
  docker compose up --build -d
```

Acesse para testar:

```bash
  http://localhost:8080/products
```

## Categorias

### Retorna todas as categorias

```http
GET /categories
```

**Descrição:** Retorna uma lista de todas as categorias cadastradas.

---

### Retorna uma categoria por ID

```http
GET /categories/{id}
```

| Parâmetro | Tipo     | Descrição                           |
| :-------- | :------- | :---------------------------------- |
| `id`      | `long`   | **Obrigatório**. ID da categoria.   |

**Descrição:** Retorna os detalhes de uma categoria específica pelo ID.

---

### Adiciona uma nova categoria

```http
POST /categories
```

| Parâmetro      | Tipo       | Descrição                        |
| :------------- | :--------- | :------------------------------- |
| `name`         | `string`   | **Obrigatório**. Nome da categoria. |

**Body:**
```json
{
  "name": "Eletrônicos"
}
```

**Descrição:** Adiciona uma nova categoria ao sistema.

---

### Atualiza uma categoria por ID

```http
PUT /categories/{id}
```

| Parâmetro | Tipo     | Descrição                           |
| :-------- | :------- | :---------------------------------- |
| `id`      | `long`   | **Obrigatório**. ID da categoria.   |

**Body:**
```json
{
  "name": "Eletrodomésticos"
}
```

**Descrição:** Atualiza os detalhes de uma categoria existente.

---

## Produtos

### Retorna todos os produtos

```http
GET /products
```

| Parâmetro   | Tipo       | Descrição                                             |
| :---------- | :--------- | :---------------------------------------------------- |
| `name`      | `string`   | **Opcional**. Filtra os produtos pelo nome.           |
| `price`     | `double`   | **Opcional**. Filtra os produtos pelo preço.          |
| `category`  | `string`   | **Opcional**. Filtra os produtos pela categoria.      |
| `sort`      | `string`   | **Opcional**. Define a ordenação (ex.: `name,asc`).   |

**Exemplo de Uso:**
- Para buscar produtos pelo nome: `/products?name=Smartphone`
- Para filtrar produtos com preço até 1000: `/products?price=1000`
- Para ordenar por nome de forma ascendente: `/products?sort=name,asc`
- Combinar filtros: `/products?name=Smartphone&price=1000&sort=price,desc`

**Descrição:** Retorna uma lista de todos os produtos, com possibilidade de filtrar e ordenar.

---

### Retorna um produto por ID

```http
GET /products/{id}
```

| Parâmetro | Tipo     | Descrição                        |
| :-------- | :------- | :------------------------------- |
| `id`      | `UUID`   | **Obrigatório**. ID do produto.  |

**Descrição:** Retorna os detalhes de um produto específico pelo ID.

---

### Adiciona um novo produto

```http
POST /products
```

| Parâmetro      | Tipo       | Descrição                        |
| :------------- | :--------- | :------------------------------- |
| `name`         | `string`   | **Obrigatório**. Nome do produto. |
| `price`        | `double`   | **Obrigatório**. Preço do produto. |
| `stock`   | `int`     | **Obrigatório**. Estoque do produto. |
| `description`   | `string`     | **Obrigatório**. Descrição do produto. |
| `categories`   | `Array[string]`     | **Obrigatório**. Categorias do produto |

**Body:**
```json
{
    "name": "Smartphone",
    "price": 200,
    "stock": 3,
    "description": "Latest model",
    "categories": ["Electronics"]
}
```

**Descrição:** Adiciona um novo produto ao sistema.

---

### Atualiza um produto por ID

```http
PUT /products/{id}
```

| Parâmetro | Tipo     | Descrição                        |
| :-------- | :------- | :------------------------------- |
| `id`      | `UUID`   | **Obrigatório**. ID do produto.  |

**Body:**
```json
{
    "name": "Smartphone",
    "price": 400,
    "stock": 1,
    "description": "Latest model",
    "categories": ["Electronics"]
}
```

**Descrição:** Atualiza os detalhes de um produto existente.

---

### Deleta um produto por ID

```http
DELETE /products/{id}
```

| Parâmetro | Tipo     | Descrição                        |
| :-------- | :------- | :------------------------------- |
| `id`      | `UUID`   | **Obrigatório**. ID do produto.  |

**Descrição:** Remove um produto do sistema.

---

## Observações

- Utilize as rotas conforme as especificações acima.
- Certifique-se de passar os parâmetros obrigatórios corretamente.
---
## Melhorias propostas

- Refatoração do código para ao excluir um produto, incluir uma flag booleana sendo false quando um produto é "excluido", apresentando o conceito de soft delete no projeto.
- Refatoração ao adicionar uma categoria, podendo ser tanto pelo nome como pelo o id da mesma.

