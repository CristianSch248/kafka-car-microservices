# kafka-car-microservices

Sistema baseado em microsserviços utilizando Spring Boot e Apache Kafka.

## Serviços

- api
    - Recebe requisições HTTP.
    - Publica mensagens no Kafka.

- car
    - Consome mensagens do Kafka.
    - Persiste os dados no banco.

- data
    - Consome mensagens do Kafka.
    - Realiza processamento e análises.

## Tecnologias

- Java 21
- Spring Boot
- Apache Kafka
- PostgreSQL