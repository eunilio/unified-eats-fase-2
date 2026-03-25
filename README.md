# 🍽️ Unified Eats API – Fase 2

Projeto desenvolvido como parte do **Tech Challenge – Pós FIAP (Arquitetura e Desenvolvimento Java)**.

Esta API fornece endpoints para gerenciamento de usuários, tipos de usuário, restaurantes e itens de cardápio, seguindo boas práticas de arquitetura REST e Clean Architecture.

---

## 🚀 Tecnologias Utilizadas

- ☕ Java 21
- 🌱 Spring Boot
- 🗄️ Spring Data JPA
- 🐬 MySQL 8
- 🐳 Docker e Docker Compose
- 📘 Swagger / OpenAPI
- 📬 Postman
- 🧪 JUnit e Mockito

---

## ▶️ Como rodar o projeto

### Pré-requisitos

- Docker
- Docker Compose

---

### Subindo a aplicação

No diretório raiz do projeto, execute:

docker compose up --build

Aguarde até que os containers estejam totalmente inicializados.

---

## 🌐 URLs importantes

- API: http://localhost:8080  
- Swagger UI: http://localhost:8080/swagger-ui/index.html  

---

## 🗄️ Banco de Dados

- Banco: MySQL 8  
- Porta: 3307  
- Database: tech_challenge  

---

## 🧪 Testes com Postman

Arquivos:

postman/
unifiedeats.postman_collection.json
local.postman_environment.json

---

## ⚙️ Funcionalidades Implementadas

### 👤 Usuários
- CRUD de usuários  
- Busca por nome  
- Validação de e-mail único  
- Autenticação  
- Alteração de senha  
- Atualização de dados  

### 🏷️ Tipos de Usuário
- CRUD de tipos de usuário  

### 🍽️ Restaurantes
- CRUD de restaurantes  

### 📋 Itens de Cardápio
- CRUD de itens  

---

## 🏗️ Arquitetura

- Domain  
- Application  
- Infrastructure  

---

## 🧪 Testes

- JUnit + Mockito  
- Cobertura ~80%  

---

## 📘 Swagger

http://localhost:8080/swagger-ui/index.html

---

## 🎓 Sobre

Tech Challenge – FIAP
