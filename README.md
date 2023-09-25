
<p align="center">
<img src="https://s3.sa-east-1.amazonaws.com/static.launchlab.com.br/d3777f6b-dce3-4934-9206-a747a0514ce0.png" height="100">
</p>
<h1 align="center">Clean Code e Clean Architecture - Turma 12</h1>
<p> <i>Projeto prático que será desenvolvido aplicando os conceitos: </i>
Aplicação que representará uma variação do aplicativo <i><b>UBER</b></i>, onde o motorista poderá se cadastrar para fazer corridas para os passageiros.
</br>  
</br>  

### [Aula 01](https://github.com/mariliamessias/branas-clean-code-arch/tree/main/aula_01) - Clean Code and Refactoring
-  Clean Code
-  Code Smells e Técnicas de Refactoring
    * <b>Nomes Ruins:</b>
      * <i>Identificação:</i> nome de variável, método, função, arquivo que não é suficientemente claro.
      * <i>Técnica aplicada para correção:</i> renomeação.
    * <b>Linhas em branco:</b>
      * <i>Técnica aplicada para correção:</i> apagar linhas em branco dentro de métodos e funções.
    * <b>Comentários:</b>
      * <i>Técnica aplicada para correção:</i> introduzir variável explicativa, extrair para métodos e funções para que não haja necessidade de explicação adicional.
   * <b>Código Morto:</b>
      * <i>Técnica aplicada para correção:</i> apagar código não utilizado.
   * <b>Condições confusas e aninhadas:</b>
      * <i>Identificação:</i> condição confusa e que é complexa a interpretação do seu comportamento.
      * <i>Técnica aplicada para correção:</i> extrair condições, introduzir cláusulas de guarda (inverter as condições e interromper o fluxo de execução para eliminar o else). Consolidar condições. Introduzir ternário.
   * <b>Tratamento inadequado de erros ou exceções:</b>
      * <i>Técnica aplicada para correção:</i> tratar adequadamente os erros ou exceções.
   * <b>Números Mágicos:</b>
      * <i>Técnica aplicada para correção:</i> extrair para constantes, extrair para variáveis explicativas.
   * <b>Método Longo</b>
   * <b>Classe Longa</b>
-  Design vs. Arquitetura
-  Projeto: <b>Calcular o preço da corrida</b>
```bash
url: /calculate_ride
method: "post"
input: distance, date
output: price
```

### [Aula 02](https://github.com/mariliamessias/branas-clean-code-arch/tree/main/aula_02) - Test-Driven Development

-  Code Smells e Técnicas de Refactoring (Refatoração do algoritmo de validação de CPF);
- Testes além de auxiliarem na consistência do código desenvolvido, auxilia na documentação;
- Estrutura base para a construção de um teste: GIVEN (o que é necessário para executar o comportamento esperado), WHEN (execução) e THEN (verificação após a execução do teste, onde é validada a assertividade do comportamento esperado);
- Testes Unitários: testes de UNIDADE, são mais rápidos e não tem uso de recursos externos;
- Testes de Integração: testes de várias camadas da aplicação, são mais lentos, tem uso de recursos externos;
- Testes E2E: testes de ponta a ponta, são mais lentos e têm mais chance de serem sucetíveis a falhas devido a dependência com a interface com o usuário;
-  Projeto: <b>Suporte a criação e consulta de motoristas e passageiros</b>.

```bash
url: /drivers
method: "post"
input: name, email, document, carPlate
output: driverId
```

```bash
url: /drivers/{driverId}
method: "get"
output: id, name, email, document, carPlate, createdAt, updatedAt
```

```bash
url: /passengers
method: "post"
input: name, email, document
output: driverId
```

```bash
url: /passengers/{passengersId}
method: "get"
output: id, name, email, document, createdAt, updatedAt
```