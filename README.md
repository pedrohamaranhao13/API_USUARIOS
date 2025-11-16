# 👥 API de Usuários – PhamTecnologia

```
API RESTful desenvolvida em Spring Boot 3 com integração ao PostgreSQL, contendo:

✔ Cadastro de usuários
✔ Autenticação com JWT Bearer Token
✔ Recuperação de senha com envio de e-mail
✔ Criptografia de senha com SHA-256
✔ Cadastro e vinculação de perfis
✔ Documentação automática via Swagger OpenAPI

```
---

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Web**
- **Spring Data JPA**
- **Spring Mail**
- **PostgreSQL**
- **Lombok**
- **Java JWT**
- **SpringDoc OpenAPI**
- **Maven**

---

## 🧩 Estrutura do Projeto
```
src/
├── main/
│   ├── java/br/com/phamtecnologia/
│   │   ├── components/        → Crypto, Email e JWT
│   │   ├── configurations/    → Configuração do Swagger
│   │   ├── controllers/       → Endpoints REST
│   │   ├── dtos/              → Request e Response
│   │   ├── entities/          → Entidades JPA
│   │   ├── exceptions/        → Exceções personalizadas
│   │   ├── repositories/      → Interfaces JPA
│   │   └── services/          → Regras de negócio
│   └── resources/
│       └── application.properties

```

---

## ⚙️ Configuração do Ambiente
### 1️⃣ Banco de Dados

Crie um banco no **PostgreSQL** com o nome:

```sql
CREATE DATABASE bd_pham_usuariosApi;
```

### 2️⃣ Configure o application.properties:
```
spring.application.name=API_Usuarios
server.port=8081

spring.datasource.url=jdbc:postgresql://localhost:5432/bd_pham_usuariosApi
spring.datasource.username=postgres
spring.datasource.password=root
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# JWT Secret
jwt.secret=MINHA_CHAVE_SECRETA_AQUI

# Configuração de e-mail
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=seuemail@gmail.com
spring.mail.password=sua_senha_app
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

```
📌 O campo jwt.secret deve ser uma chave forte.

📌 Para Gmail, é necessário gerar Senha de App.

### 3️⃣ Executar a Aplicação

```
mvn spring-boot:run
```
A API será iniciada em:
http://localhost:8081/swagger-ui/index.html#/

🧠 Endpoints Principais

👤 Usuário
```
Método	Endpoint	                Descrição
POST	/api/v1/usuario/cadastrar	Cadastra um novo usuário
POST	/api/v1/usuario/autenticar	Autentica o usuário (em desenvolvimento)
POST	/api/v1/usuario/recuperar	Recupera a senha (em desenvolvimento)
POST    /api/v1/perfil              Cadastrar perfil
```

Exemplo de Requisição (POST /api/v1/perfil)
```
{
"nome": "ATENDENTE"
}
```
Exemplo de Requisição (POST /api/v1/usuario/cadastrar)
```
{
"nome": "João da Silva",
"telefone": "(27) 99999-0000",
"email": "joao@phamtecnologia.com.br",
"senha": "123456",
"perfil": "ATENDENTE"
}
```
Exemplo de Resposta
```
{
  "id": "f2a3c410-6a7a-4e1b-9a33-8c13f45b6a72",
  "nome": "João da Silva",
  "telefone": "(27) 99999-0000",
  "email": "joao@phamtecnologia.com.br",
  "perfil": "ATENDENTE",
  "dataHoraCriacao": "2025-11-07T14:33:10.238"
}

```

👨‍💻 Autor

Pedro Maranhão

📧 pedro.maranhao@yahoo.com.br

🌐 https://www.phamtecnologia.com.br
