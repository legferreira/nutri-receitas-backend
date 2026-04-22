# NutriReceitas — Backend

API REST desenvolvida com Java 21 + Spring Boot 3.3.5 + PostgreSQL.

## Pré-requisitos

- Java 21 ou superior
- PostgreSQL rodando localmente na porta `5432`
- Banco de dados `nutrireceitas` criado com usuário `postgres` / senha `1910`

## Como rodar

```bash
# Na pasta api/
./mvnw spring-boot:run
```

Windows:
```cmd
mvnw.cmd spring-boot:run
```

A API sobe em: `http://localhost:8080`

## Endpoints principais

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | /api/receitas | Lista todas as receitas |
| GET | /api/receitas/publicas | Lista receitas publicadas |
| POST | /api/receitas | Cria receita |
| GET | /api/pacientes | Lista pacientes |
| POST | /api/pacientes | Cria paciente |

## Configuração do banco

As tabelas são criadas automaticamente pelo Hibernate na primeira execução (`ddl-auto=update`).

Para alterar as credenciais, edite `api/src/main/resources/application.properties`.
