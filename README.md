# evoluum [![Build Status](https://travis-ci.org/edgarpf/evoluum.svg?branch=master)](https://travis-ci.org/edgarpf/evoluum) [![Maintainability](https://api.codeclimate.com/v1/badges/1cbe50edb7d616c2b47b/maintainability)](https://codeclimate.com/github/edgarpf/evoluum/maintainability)

## Rodando o projeto

Com o Maven e o Java instalados na sua máquina faça:

```js
mvn clean package
java -jar target/localidade-0.0.1-SNAPSHOT.jar
```

Vá para http://localhost:8080/swagger-ui.html para testar os endpoints:

## /api/evoluum/localidade/municipio/{nomeCidade}

Esse endpoint irá retornar os ids indicados pelo nomeCidade (é possível existir cidades de mesmo nome em estados diferentes). 
As respostas são armazenas em cache que é reiniciado a cada minuto.

## /api/evoluum/localidade/todos/{retorno}

Esse endpoint irá retornar dados de todos os municípios de acordo com a documentação. 
Caso haja timeout de 15 seg uma lista vazia é retornada.
