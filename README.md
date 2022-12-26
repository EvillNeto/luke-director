<h1 align="center">Luke Director </h1>

 Api de Demontração de integração com api [Swapi](https://swapi.dev/)

### :zap: Atalhos de uso:

- [Swagger Ui](http://localhost:8080/swagger-ui/index.html)

- [Banco H2](http://localhost:8080/h2-console)

### :rocket: Setup
- é recomendado possuir a uma versão do Java igual ou superior a 17, pois a api utiliza o Java 17
- A api vem com todas as configurações prontas para a porta 8080 e só é necessario lançala pela sua IDE de preferencia ou atraves do terminal

### :pushpin: Desafios:

- Desafio 1: 
    - [x] Paginação com 10 itens por página
    - [x] Criar um filtro para busca por title e episode_id
    - [x] O retorno do campo “release_date” ser no formato dd/MM/yyyy
- Desafio 2:
    - [x] Escrever a nova listagem de filmes no console da aplicação
    - [x] Salvar a nova listagem de filmes em um banco de dados H2 Database
    - [] Implementação de testes unitários

### :bookmark_tabs: Funcionalidades

- A api possui [Swagger](http://localhost:8080/swagger-ui/index.html) configurado com descrições para cada metodo
- O banco de dados [H2](http://localhost:8080/h2-console) pode ser acessado com um atalho na tela do swagger e possui as configurações:
~~~
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.url=jdbc:h2:mem:lukedb
spring.datasource.username=luke
spring.datasource.password=
~~~
- A api é dividida em duas versões, uma para cada desafio, uma a v1 busca em tempo real as informações da [Swapi](https://swapi.dev/).
Enquanto a v2 da a opção de popular o banco com informações proprias ou com as informações da [Swapi](https://swapi.dev/).

### :dart: Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

- [Git](https://git-scm.com)
- [Java](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Spring](https://spring.io/)
- Spring Webflux
- Jpa Specification