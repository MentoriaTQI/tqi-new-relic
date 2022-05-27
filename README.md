Conexao TQI - New Relic (Observabilidade)

##### Passos para rodar o microserviço (local)
```sh
# Subir o banco de dados utilizando a imagem docker
docker-compose down && docker-compose up -d

# Fazer o build do pacote
./gradlew clean build

# Subir o microserviço e injetar o agent do New Relic no pacote
java -javaagent:newrelic.jar -jar build/libs/*.jar
```

##### Tecnologias utilizadas

* Spring boot
* AWS Lambda (Python)
* AWS S3 (Armazenamento)
* AWS SQS (Fila)
* Postgres (Docker)

##### Roteiro para criação de conta NewRelic

* Cadastro no site
* Login após liberação do cadastro
* Capturar a chave nos dados de sua conta
* Com a chave em mãos, adicione no arquivo newrelic.yml no campo "license_key"
* Execute a sua aplicação injetando o agent do NewRelic (newrelic.jar)

### Arquitetura das integrações

![image](https://user-images.githubusercontent.com/93996378/170743954-86c614aa-197b-4b4a-8993-eb03232c90ac.png)
