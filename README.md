<h1 align="center">Family Money Tracker API </h1>

<h4 align="center"> 
	🚧  Em construção... 🚧
</h4>

<p align="center">
 <a href="#-sobre-o-projeto">Sobre</a> •
 <a href="#-funcionalidades">Funcionalidades</a> •
 <a href="#-como-executar-o-projeto">Como executar</a> • 
 <a href="#-tecnologias">Tecnologias</a> • 
 <a href="#-autora">Autora</a>
</p>


## 💻 Sobre o projeto

O Family Money Tracker é uma aplicação web voltada a familias que querem registrar, analisar e controlar seus ganhos e seus gastos a fim de poder ter uma visão geral da saúde financeira da família ao longo do tempo e poder tomar decisões baseadas nesses dados. Além disso, a ideia é permitir que não só os pais, mas também os filhos possam utilizar a aplicação para ajudar nos registros dos ganhos e gastos da família.

Esta aplicação está sendo concebida e desenvolvida durante a disciplina *Projeto Integrado* da Pós Graduação em Engenharia de Software da PUC Minas.

Este repositório contém o backend da aplicação, que consiste de uma API REST que utiliza um banco de dados PostgreSQL. 

*Para acessar o repositório do frontend, [clique aqui.](https://github.com/CrisJacques/family-money-tracker)*

---

## ⚙️ Funcionalidades

Até o momento, as seguintes funcionalidades foram desenvolvidas e já estão disponíveis para uso:
- [x] Autenticação e autorização:
  - [x] 3 perfis de usuário:
    - [x] Administrador do sistema
    - [x] Administrador de grupo
    - [x] Usuário comum
- [x] Cadastro de novos usuários:
  - Nesta versão, é possível apenas criar usuários de perfil Administrador de grupo, para fins de testes. Todos os novos usuários serão adicionados ao grupo "A Grande Família" (ou seja, a criação de novos grupos de usuários não foi implementada nesta versão).
- [x] CRUD de receitas
  - [x] Aplicação permite o cadastro, visualização, edição e exclusão de receitas (ou seja, dinheiro que entra nas contas da família, por exemplo salários, renda extra, etc)
  - Usuários administradores de grupo podem criar, editar, visualizar e excluir receitas. Já usuários comuns apenas podem criar e visualizar receitas.
- [x] CRUD de despesas
  - [x] Aplicação permite o cadastro, visualização, edição e exclusão de despesas, contemplando diversas formas de pagamento (débito, dinheiro, cartão de crédito, financiamento e empréstimo)
  - Usuários administradores de grupo podem criar, editar, visualizar e excluir despesas. Já usuários comuns apenas podem criar e visualizar despesas.
- [x] Visualização do resumo de despesas e receitas do mês atual 
    - [x] Na tela inicial da aplicação, é exibido um resumo financeiro do mês atual:
      - [x] Despesas e receitas recentes (os 5 últimos registros de cada tipo dentro do mês atual)
      - [x] Somatório de despesas e receitas por categoria, com gráficos ilustrando a parcela do todo que cada categoria representa
      - [x] Somatório total de despesas e receitas para o mês atual, informando também o saldo resultante
- [x] Listagem de despesas para um período selecionado pelo usuário 
- [x] Listagem de receitas para um período selecionado pelo usuário 
- [x] Relatório de resumo de despesas e receitas para um período selecionado pelo usuário 
	- [x] Despesas e receitas do período
	- [x] Somatório de despesas e receitas por categoria, com gráficos ilustrando a parcela do todo que cada categoria representa
	- [x] Somatório total de despesas e receitas para o período selecionado, informando também o saldo resultante

---

## 🚀 Como executar o projeto

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
- [Git](https://git-scm.com)
- [Java 11](https://www.oracle.com/java/technologies/downloads/#java11)
- [Spring Tool Suite](https://spring.io/tools)
- [Banco PostgreSQL](https://www.postgresql.org/)

#### 🎲 Rodando a API

1. Clone este repositório
```bash
$ git clone https://github.com/CrisJacques/family-money-tracker-api.git

```
2. Crie um banco de dados PostgreSQL de nome *family-money-tracker*. Abaixo, um exemplo de como fazer isso no *pgAdmin 4*:
![image](https://user-images.githubusercontent.com/66973973/169429573-9f55d57a-4694-4842-9d19-9bd9a1f8aa4f.png)

3. Abra a pasta raiz do projeto no *Spring Tool Suite*
4. Descomente a linha que possui o @PostConstruct (linha 89 do arquivo *PopulateData.java*) e salve para que o banco de dados seja populado com dados de teste
5. Insira o usuário e senha de conexão com o seu PostgreSQL no arquivo *application.properties* e salve:
![image](https://user-images.githubusercontent.com/66973973/169429688-9479d01b-e16b-4bb4-9604-e4dcbaeda2a5.png)

6. Clique com o botão direito sobre o projeto e selecione *Run As > Spring Boot App*
![image](https://user-images.githubusercontent.com/66973973/168493988-0ad4eb91-0432-4b5b-8891-5ec0caa01440.png)
7. Comente novamente a linha que contém o @PostConstruct no *PopulateData.java* e salve para evitar que o banco seja populado com dados repetidos toda vez que subir a aplicação.

---

## 🛠 Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

-   **[Spring Boot](https://spring.io/projects/spring-boot)**
-   **[Spring Security](https://spring.io/projects/spring-security)**
-   **[Spring Data JPA](https://spring.io/projects/spring-data-jpa)**
-   **[H2 Database - para desenvolvimento e testes](https://www.h2database.com/html/main.html)** 
-   **[Banco PostgreSQL - para deploy e testes no Heroku](https://www.postgresql.org/)** 

---

## 🦸 Autora

Cristhiane Jacques

👋🏽 [Entre em contato!](https://www.linkedin.com/in/cristhiane-jacques/)

