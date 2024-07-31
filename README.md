
<h3 align="center">
  Backend PicPay Challenge
</h3>

<p align="center">

  <img alt="License: MIT" src="https://img.shields.io/badge/license-MIT-%2304D361">
  <img alt="Language: Java" src="https://img.shields.io/badge/language-java-green">
  <img alt="Version: 1.0" src="https://img.shields.io/badge/version-1.0-yellowgreen">


## :rocket: Technologies

* Java 21
* Spring Boot
* Spring Data JPA
* MySQL
* Docker
* Spring Cloud Open Feign
* ControllerAdvice & Problem Details
* Hibernate Validator

## Objective: PicPay Simplified

PicPay Simplified is a simplified payments platform. It is possible to deposit and make transfers
of money between users. We have 2 types of users, common users and retailers, both have wallets with money and carry out
transfers between them.

## Requirements

- For both types of user, we need the `Full Name`, `CPF`, `e-mail` and `Password`. CPF/CNPJ and emails must be
  unique in the system. Therefore, your system must only allow one registration with the same CPF or email address;

- Users can send money (transfer) to merchants and between users;

- Merchants **only receive** transfers, they do not send money to anyone;

- Validate whether the user has a balance before the transfer;

- Before finalizing the transfer, you must consult an external authorizing service, use this mock
  [https://run.mocky.io/v3/dd73ee78-cc03-4b80-84d4-cdd1fb30059f](https://run.mocky.io/v3/dd73ee78-cc03-4b80-84d4-cdd1fb30059f) to simulate the service
  using the verb `GET`;

- The transfer operation must be a transaction (i.e., rolled back in any case of inconsistency) and the
  money must return to the sending user's wallet;

- Upon receipt of payment, the user or retailer must receive notification (email, SMS) sent by a
  third-party service and this service may eventually be unavailable/unstable. Use this mock
  [https://run.mocky.io/v3/fa297024-09a6-4309-945a-4dfecf1f924b](https://run.mocky.io/v3/fa297024-09a6-4309-945a-4dfecf1f924b) to simulate sending the notification
  using the verb `POST`;

- This service must be RESTFul.

### Endpoint Transfer

The implementation must follow the contract below.

```http request
POST /transfer
Content-Type: application/json

{
  "value": 100.0,
  "payer": 4,
  "payee": 15
}
```