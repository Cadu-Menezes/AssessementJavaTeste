
# Como Executar os Testes - Guia por Exercício

## Necessario
- Java 11 ou superior
- Maven instalado
- Chrome browser (para testes Selenium)

## **Exercício 1 - Testes de IMC**

```bash
# Testes Exploratórios
mvn test -Dtest=TestesExploratorios

# Testes de Partição de Equivalência
mvn test -Dtest=TestesParticaoEquivalencia

# Testes de Valor Limite
mvn test -Dtest=TestesValorLimite
```

---

## **Exercício 2 - MathFunctions (Testes Baseados em Propriedades)**

```bash
# Testes com jqwik (Property-Based Testing)
mvn test -Dtest=TestesJqwikPropriedades
```

---

## **Exercício 3 - API ViaCEP (Testes de Integração)**

```bash
# Testes de Entradas Inválidas
mvn test -Dtest=TestesEntradasInvalidas

# Testes de Consulta por Endereço
mvn test -Dtest=TestesConsultaEndereco

# Testes baseados em Tabela de Decisão
mvn test -Dtest=TestesTabelaDecisao

# Testes de Partição e Valor Limite
mvn test -Dtest=TestesParticaoValorLimite
```

---

## **Exercício 4 - Selenium (Testes de Interface Web)**

```bash
# Testes de Cadastro e Login (versão original)
mvn test -Dtest=TestesCadastroLogin

# Testes de Login Inválido (versão original)
mvn test -Dtest=TestesLoginInvalido

# Testes com Page Object Model (refatorado)
mvn test -Dtest=TestesCadastroLoginPOM

# Testes de Login Inválido com POM (refatorado)
mvn test -Dtest=TestesLoginInvalidoPOM

# Testes de Simulação de Erros
mvn test -Dtest=TestesSimulacaoErros
```

---

## **Exercício 5 - Análise Estrutural (Factorial)**

```bash
# Testes Unitários do Factorial
mvn test -Dtest=FactorialTest

# Gerar Relatório de Cobertura com JaCoCo
mvn clean test -Dtest=FactorialTest

# Visualizar relatório HTML de cobertura
# Abrir no navegador: target/site/jacoco/index.html
```

---

## **Executar Todos os Testes do Projeto**

```bash
mvn clean test
```

---

## **Gerar Relatório Completo de Cobertura**

```bash
mvn clean test
# Relatório disponível em: target/site/jacoco/index.html
```
