<h1 align="center">Family Money Tracker API </h1>

<h4 align="center"> 
	🚧  Em construção... 🚧
</h4>

<p align="center">
 <a href="#-sobre-o-projeto">Sobre</a> •
 <a href="#-funcionalidades">Funcionalidades</a> •
 <a href="#-como-executar-o-projeto">Como executar</a> • 
 <a href="#-tecnologias">Tecnologias</a> • 
 <a href="#-autor">Autora</a>
</p>


## 💻 Sobre o projeto

O Family Money Tracker é uma aplicação web voltada a familias que querem registrar, analisar e controlar seus ganhos e seus gastos a fim de poder ter uma visão geral da saúde financeira da família ao longo do tempo e poder tomar decisões baseadas nesses dados. Além disso, a ideia é permitir que não só os pais, mas também os filhos possam utilizar a aplicação para ajudar nos registros dos ganhos e gastos da família.

Esta aplicação está sendo concebida e desenvolvida durante a disciplina *Projeto Integrado* da Pós Graduação em Engenharia de Software da PUC Minas.

Este repositório contém o backend da aplicação, que consiste de uma API REST que utiliza um banco de dados MySQL. 

*Para acessar o repositório do frontend, [clique aqui.](https://github.com/CrisJacques/family-money-tracker)*

---

## ⚙️ Funcionalidades

Até o momento, as seguintes funcionalidades foram desenvolvidas e já estão disponíveis para uso:
- [x] Autenticação e autorização:
  - [x] 3 perfis de usuário:
    - [x] Administrador do sistema
    - [x] Administrador de grupo
    - [x] Usuário comum
  - Por enquanto, a maior diferença entre esses perfis é quais opções eles conseguem acessar no menu principal do sistema.
  - Nesta versão, ainda não é possível criar novos usuários. Para fins de testes, foram cadastrados 3 usuários no banco de dados, um de cada perfil.
- [x] Cadastro de receitas
  - [x] Aplicação já permite o cadastro de receitas (ou seja, dinheiro que entra nas contas da família, por exemplo salários, renda extra, etc)
  - Por enquanto, ainda não é possível cadastrar contas nem categorias de receitas além das já cadastradas no banco de dados para fins de testes e demonstrações.
- [x] Cadastro de despesas
  - [x] Aplicação já permite o cadastro de despesas, contemplando diversas formas de pagamento (débito, dinheiro, cartão de crédito, financiamento e empréstimo)
  - Por enquanto, ainda não é possível cadastrar cartões de crédito nem categorias de despesas além das já cadastradas no banco de dados para fins de testes e demonstrações.
- [x] Visualização do resumo de despesas e receitas do mês atual 
    - [x] Na tela inicial da aplicação, é exibido um resumo financeiro do mês atual:
      - [x] Despesas e receitas recentes (os 5 últimos registros de cada tipo dentro do mês atual)
      - [x] Somatório de despesas e receitas por categoria, com gráficos ilustrando a parcela do todo que cada categoria representa
      - [x] Somatório total de despesas e receitas para o mês atual, informando também o saldo resultante

Além das funcionalidades listadas acima, que já estão disponíveis para uso a partir do frontend, também foram desenvolvidas outras funcionalidades que por enquanto não estão disponíveis via frontend, mas que já possuem endpoints funcionais no backend:
- [x] Cadastro (POST), Leitura (GET/id), Alteração (PUT), Exclusão (DELETE) e Listagem de todos os itens (GET) das seguintes entidades:
  - [x] Categorias de despesas (por enquanto estão disponíveis no frontend algumas categorias pré-cadastradas no banco de dados)
  - [x] Categorias de receitas (por enquanto estão disponíveis no frontend algumas categorias pré-cadastradas no banco de dados)
  - [x] Receitas (via frontend por enquanto só é possível cadastrar e visualizar)
  - [x] Despesas (via frontend por enquanto só é possível cadastrar e visualizar)
  - [x] Contas (por enquanto estão disponíveis no frontend algumas contas pré-cadastradas no banco de dados)
  - [x] Cartões de crédito (por enquanto estão disponíveis no frontend alguns cartões de crédito pré-cadastrados no banco de dados)

---

## 🚀 Como executar o projeto

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
- [Git](https://git-scm.com)
- [Java 11](https://www.oracle.com/java/technologies/downloads/#java11)
- [Spring Tool Suite](https://spring.io/tools)
- [Banco MySQL](https://dev.mysql.com/downloads/mysql/)

#### 🎲 Rodando a API

1. Clone este repositório
```bash
$ git clone https://github.com/CrisJacques/family-money-tracker-api.git

```
2. Abra a pasta raiz no *Spring Tool Suite*
3. Clique com o botão direito sobre o projeto e selecione *Run As > Spring Boot App*
![image](https://user-images.githubusercontent.com/66973973/168493988-0ad4eb91-0432-4b5b-8891-5ec0caa01440.png)

---

## 🛠 Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

-   **[Spring Boot](https://spring.io/projects/spring-boot)**
-   **[Spring Security](https://spring.io/projects/spring-security)**
-   **[Spring Data JPA](https://spring.io/projects/spring-data-jpa)**
-   **[H2 Database - para desenvolvimento e testes](https://www.h2database.com/html/main.html)** 

---

## 🦸 Autora

Cristhiane Jacques

👋🏽 [Entre em contato!](https://www.linkedin.com/in/cristhiane-jacques/)

