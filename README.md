# FIAP Connect (API)

API Spring Boot (H2 em memória) para cadastro de Usuários e Grupos.

## Como rodar
\`\`\`bash
./mvnw spring-boot:run
\`\`\`

- H2 Console: http://localhost:8080/h2  
  - JDBC URL: `jdbc:h2:mem:fiap`
  - User: `SA` (sem senha)

## Endpoints
- `POST /usuarios` | `GET /usuarios` | `GET /usuarios?rm=...&nome=...`
- `POST /grupos`   | `GET /grupos`   | `GET /grupos?status=...&nomeProjeto=...`
