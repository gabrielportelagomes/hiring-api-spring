# hiring-api-spring

## Sobre
API que gerencia as etapas de um processo seletivo, densenvolvida durante bootcamp promovido pela IBM.

## Tecnologias

- Java 17;
- Spring Boot 3.1.1;
- MySQL;
- Maven;
- JUnit;


## Instalação

1. Clone este repositório:

```bash
git clone git@github.com:gabrielportelagomes/hiring-api-spring.git
```

2. Instale as dependências com Maven

## Como executar

1. Verifique as informações de conexão com o banco de dados MySQL em /src/main/resources/application.properties
2. Inicie a aplicação com o Maven
3. A API estará disponível em `http://localhost:8080`


## API Endpoints
A API apresenta os seguintes endpoints:

```
POST /api/v1/hiring/start
Recebe um JSON no seguinte formato:
  {
	  "nome": "Nome do Candidato"
  }

Retorna em caso de sucesso:
  {
	  "codCandidato": 1
  }
```

```
POST /api/v1/hiring/schedule
Recebe um JSON no seguinte formato:
  {
	  "codCandidato": 1
  }
```

```
POST /api/v1/hiring/disqualify
Recebe um JSON no seguinte formato:
  {
	  "codCandidato": 1
  }
```

```
POST /api/v1/hiring/approve
Recebe um JSON no seguinte formato:
  {
	  "codCandidato": 1
  }
```

```
GET /api/v1/hiring/status/candidate/:id
Retorna o status do candidato com o id(codCandidato) informado, no seguinte formato:
  {
    "status": "Aprovado"
  }
```

```
GET /api/v1/hiring/approved
Retorna uma lista de candidatos, no seguinte formato:
  [
	  {
		"id": 1,
		"nome": "Nome do Candidato",
		"status": "Aprovado"
	  }
  ]
```

## Como executar os testes unitários

1. Realize os passos do guia de Instalação
2. Execute os testes com o Maven

## Repositório do front
- [hiring-front](https://github.com/gabrielportelagomes/hiring-front)

