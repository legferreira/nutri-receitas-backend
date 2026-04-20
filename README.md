# NutriReceitas — Backend Java Spring

## Como rodar

1. Certifique-se que o PostgreSQL está rodando com o banco `nutrireceitas` criado
2. Copie os arquivos deste zip para dentro da pasta `src` e `README.md` do projeto gerado pelo Spring Initializr
3. No terminal do VS Code, dentro da pasta `api`:

```
./mvnw spring-boot:run
```

No Windows use:
```
mvnw.cmd spring-boot:run
```

O backend sobe em: http://localhost:8080

## Endpoints disponíveis

### Receitas
- GET    http://localhost:8080/api/receitas
- GET    http://localhost:8080/api/receitas/publicas
- GET    http://localhost:8080/api/receitas/{id}
- POST   http://localhost:8080/api/receitas
- PUT    http://localhost:8080/api/receitas/{id}
- DELETE http://localhost:8080/api/receitas/{id}

### Pacientes
- GET    http://localhost:8080/api/pacientes
- GET    http://localhost:8080/api/pacientes/{id}
- POST   http://localhost:8080/api/pacientes
- PUT    http://localhost:8080/api/pacientes/{id}
- DELETE http://localhost:8080/api/pacientes/{id}
- GET    http://localhost:8080/api/pacientes/{id}/historico-peso
- POST   http://localhost:8080/api/pacientes/{id}/historico-peso
- DELETE http://localhost:8080/api/pacientes/{id}/historico-peso/{registroId}
