# Credit Analysis Microservice

[![CI](https://github.com/MateuSkywalker90/credit-analysis-microservice/actions/workflows/ci.yml/badge.svg)](https://github.com/MateuSkywalker90/credit-analysis-microservice/actions/workflows/ci.yml)

Consumes pending credit proposals from RabbitMQ, scores them, and publishes the result (approved or denied, with a reason) back to RabbitMQ.

Part of an event-driven credit proposal system — see [proposal-app](https://github.com/MateuSkywalker90/proposal-app) for the full architecture. The other consumer is [notification-microservice](https://github.com/MateuSkywalker90/notification-microservice).

## How the analysis works

Each rule is a separate `ScoreCalculation` implementation (Strategy pattern). Spring injects all of them, the service sums their scores, and a proposal is **approved when the total is above 350**. A rule can also reject a proposal outright by throwing `StrategyException`, whose message becomes the denial reason.

| Rule | Score |
|---|---|
| Bad credit check | rejects the proposal, or +100 |
| Customer credit score | rejects if < 300; otherwise +150 / +180 / +220 |
| No other loans in progress | +80 |
| Income greater than requested amount | +100 |
| Payment term under 120 months | +80 |

> The credit bureau, credit score and active-loans lookups are **simulated with random values** — in a real system they would be calls to external providers.

Adding a new rule means adding a new `@Component` that implements `ScoreCalculation`; the service doesn't change.

## Messaging

| | Name |
|---|---|
| Consumes from queue | `pending-proposal.ms-credit-analysis` |
| Publishes to exchange | `concluded-proposal.ex` (fanout) |

Failed message processing is retried up to 3 times (`spring.rabbitmq.listener.simple.retry`).

## Tech stack

Java 17 · Spring Boot 3.4 · Spring AMQP (RabbitMQ) · Lombok · JUnit 5 · Mockito

## Running

Requires RabbitMQ on `localhost:5672` (the `docker-compose.yaml` in proposal-app starts one) and Java 17.

```bash
./mvnw spring-boot:run
```

Tests (no broker needed):

```bash
./mvnw test
```
