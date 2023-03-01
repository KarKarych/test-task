# Тестовое задание Криптобиржа

## Что сделано
- Подключена PostgreSQL
- Поддержка xml/json в зависимости от хедера
- Добавлен сваггер. [OpenApi](https://gitlab.com/smetaninivanu/openapi/-/blob/main/openApi2.yaml)
- Подключён Spring Security для проверки ролей
- Реализовано подтверждение аккаунта через письмо по почте

## Демонстрация
[Видео](https://www.youtube.com/watch?v=koKxhIK_qoM)

## API приложения

secret_key передаётся в хедере Authorization secret_key

### 1. Регистрация нового пользователя 
### Эндпоинт POST /api/users
#### Доступно для незарегистрированного пользователя
Запрос
```http request
POST /api/users
```
```json
{
	"username": "ivan",
	"email": "smetaninIvanU@gmail.com"
}
```
Ответ
```json
{
	"secret_key": "YmlT59VjOJXbvrdfL94WmL2K2CX82D5K3mi"
}
```

### 2. Просмотр баланса своего кошелька
### Эндпоинт GET /api/accounts/balance
#### Доступно для зарегистрированного пользователя с ролью USER, ADMIN
Запрос
```http request
GET /api/accounts/balance
```
Ответ
```json
{
  "BTC_wallet": "0.0031589",
  "TON_wallet": "254.87",
  "RUB_wallet": "3500"
}
```

### 3. Пополнение кошелька
### Эндпоинт POST /api/accounts/deposit
#### Доступно для зарегистрированного пользователя с ролью USER, ADMIN
Запрос
```http request
POST /api/accounts/deposit
```
```json
{
  "RUB_wallet": "1000"
}
```
Ответ
```json
{
  "RUB_wallet": "2000"
}
```

### 4. Вывод денег с биржи
### Эндпоинт POST /api/accounts/withdraw
#### Доступно для зарегистрированного пользователя с ролью USER, ADMIN
Запрос
```http request
POST /api/accounts/withdraw
```
```json
{
  "currency" : "RUB",
  "count": "12",
  "credit_card" : "5365 7589 7649 2190"
}
```
или
```json
{
  "currency" : "RUB",
  "count": "12",
  "wallet" : "wqewafrt342w5awegfswaegtwta34"
}
```
Ответ
```json
{
  "RUB_wallet": 4812
}
```

### 5. Просмотр актуальных курсов валют
### Эндпоинт GET /api/currencies/{currency}/exchangeRates
#### Доступно для зарегистрированного пользователя с ролью USER, ADMIN
Запрос
```http request
GET /api/currencies/RUB/exchangeRates
```
Ответ
```json
{
  "BTC": 0.01,
  "TON": 0.008264462809917355
}
```

### 6.
### Эндпоинт POST /api/accounts/transfer
#### Доступно для зарегистрированного пользователя с ролью USER, ADMIN
Запрос
```http request
POST /api/accounts/transfer
```
```json
{
    "currency_from" : "RUB",
    "amount" : 1000,
    "currency_to": "BTC"
}
```
Ответ
```json
{
    "currency_from": "RUB",
    "currency_to": "BTC",
    "amount": 1000,
    "amount_to": 10.00
}
```

### 7. Изменить курс валют
### Эндпоинт POST /api/currencies/{currency}/exchangeRates
#### Доступно для зарегистрированного пользователя с ролью ADMIN
Запрос
```http request
POST /api/currencies/TON/exchangeRates
```
```json
{
    "BTC": 0.3234233,
    "RUB": 121
}
```
Ответ
```json
{
  "RUB": 121,
  "BTC": 0.3234233
}
```

### 8. Посмотреть общую сумму на всех пользовательских счетах для указанной валюты
### Эндпоинт GET /api/accounts/totalAmount/{currency}
#### Доступно для зарегистрированного пользователя с ролью ADMIN
Запрос
```http request
GET /api/accounts/totalAmount/RUB
```
Ответ
```json
{
    "RUB": 288745.3
}
```

### 9. Посмотреть число транзакций в заданный временной диапазон
### Эндпоинт GET /api/history/transactionCount
#### Доступно для зарегистрированного пользователя с ролью ADMIN
Запрос
```http request
GET /api/history/transactionCount
```
```json
{
    "date_from": "01.03.2023",
    "date_to": "01.03.2023"
}
```
Ответ
```json
{
    "transaction_count": 9
}
```