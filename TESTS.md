# Testes Unitários - Bidding API

Este documento explica como executar e entender os testes unitários da aplicação.

## Estrutura dos Testes

```
src/test/java/
├── com/becxagy/book/api/
│   ├── application/service/          # Testes da camada de aplicação
│   │   ├── CreateBiddingServiceTest.java
│   │   ├── ReadBiddingServiceTest.java
│   │   └── UploadBiddingServiceTest.java
│   ├── config/                       # Configurações para testes
│   │   └── TestConfig.java
│   ├── core/domain/                  # Testes da camada de domínio
│   │   └── checklist/
│   │       ├── DocumentRequirementTest.java
│   │       └── ExigenceEnumTest.java
│   ├── infra/
│   │   ├── in/                       # Testes de controllers
│   │   │   ├── BiddingResourceTest.java
│   │   │   └── exceptionhandler/
│   │   │       └── GlobalExceptionHandlerTest.java
│   │   └── out/persistence/jpa/converter/
│   │       └── ExigenceEnumConverterTest.java
│   ├── shared/
│   │   ├── exception/                # Testes de exceções customizadas
│   │   │   └── S3StorageExceptionTest.java
│   │   └── utils/validation/         # Testes de validadores
│   │       └── FileValidatorTest.java
│   └── ApplicationTests.java         # Teste de contexto da aplicação
```

## Como Executar os Testes

### Executar todos os testes
```bash
mvn test
```

### Executar testes específicos por pacote
```bash
# Testes da camada de aplicação
mvn test -Dtest="com.becxagy.book.api.application.service.*"

# Testes da camada de domínio
mvn test -Dtest="com.becxagy.book.api.core.domain.*"

# Testes de infraestrutura
mvn test -Dtest="com.becxagy.book.api.infra.*"
```

### Executar um teste específico
```bash
mvn test -Dtest="CreateBiddingServiceTest"
```

### Executar com relatório de cobertura
```bash
mvn test jacoco:report
```

## Configuração de Testes

### Profile de Teste
Os testes utilizam o profile `test` que:
- Usa banco H2 em memória
- Configura AWS com valores de teste
- Ativa logs de debug para depuração

### Mocks e Stubs
- **StoragePort**: Mockado para simular operações S3
- **QueuePort**: Mockado para simular operações SQS
- **BiddingRepository**: Mockado para simular operações de banco

## Tipos de Testes

### 1. Testes Unitários (Unit Tests)
- **CreateBiddingServiceTest**: Testa a lógica de criação de licitações
- **ReadBiddingServiceTest**: Testa a lógica de leitura de licitações
- **UploadBiddingServiceTest**: Testa o upload de arquivos
- **FileValidatorTest**: Testa validação de arquivos

### 2. Testes de Integração (Integration Tests)
- **BiddingResourceTest**: Testa endpoints REST com MockMvc
- **ApplicationTests**: Testa se o contexto Spring carrega corretamente

### 3. Testes de Componente (Component Tests)
- **GlobalExceptionHandlerTest**: Testa tratamento de exceções
- **ExigenceEnumConverterTest**: Testa conversão JPA

## Casos de Teste Cobertos

### Casos Positivos ✅
- Criação de licitação com dados válidos
- Upload de arquivos PDF e DOCX válidos
- Recuperação de licitações existentes
- Conversão correta de enums

### Casos Negativos ❌
- Arquivo muito grande
- Tipo de arquivo inválido
- Arquivo vazio ou nulo
- Dados obrigatórios ausentes
- Exceções de S3 e SQS

### Casos de Borda 🔍
- Arquivos no limite de tamanho
- Strings vazias vs null
- Exceções encapsuladas em CompletionException

## Métricas de Qualidade

### Cobertura de Código
- **Meta**: > 80% de cobertura
- **Foco**: Lógica de negócio crítica
- **Exclusões**: Configurações e DTOs simples

### Convenções
- **Padrão AAA**: Arrange, Act, Assert
- **Naming**: shouldDoSomethingWhenCondition
- **Mocks**: Uso mínimo e específico
- **Assertions**: Claras e específicas

## Executar em Diferentes Ambientes

### Local (IDE)
```bash
# IntelliJ IDEA / VS Code
Right-click → Run Tests
```

### CI/CD Pipeline
```bash
mvn clean test -Dspring.profiles.active=test
```

### Docker
```bash
docker run --rm -v $(pwd):/app -w /app maven:3.9-openjdk-21 mvn test
```

## Troubleshooting

### Problemas Comuns

1. **Testes falhando por dependências AWS**
   - Verificar se o profile `test` está ativo
   - Confirmar que os mocks estão configurados

2. **Erro de contexto Spring**
   - Verificar configurações no `application-test.properties`
   - Confirmar que o H2 está no classpath

3. **Testes de validação falhando**
   - Verificar se `spring-boot-starter-validation` está incluído
   - Confirmar configuração dos validators

### Logs Úteis
```properties
# Adicionar no application-test.properties para debug
logging.level.org.springframework.test=DEBUG
logging.level.org.mockito=DEBUG
```

## Contribuição

Ao adicionar novos testes:

1. **Siga a estrutura de pacotes existente**
2. **Mantenha o padrão de nomenclatura**
3. **Inclua casos positivos e negativos**
4. **Documente casos de teste complexos**
5. **Atualize este README se necessário**
