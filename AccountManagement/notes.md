
end points

CLIENTS
GET : http/localhost:8080/clients
GET : http/localhost:8080/clients/{clientId}

ACCOUNTS
GET : http/localhost:8080/accounts
GET : http/localhost:8080/accounts/account/{accountId}
GET : http/localhost:8080/accounts/client-account/{clientId}
POST : http/localhost:8080/accounts
PUT : http/localhost:8080/accounts/{accountId}
DELETE : http/localhost:8080/accounts/{accountId}

CARDS
GET : http/localhost:8080/cards
GET : http/localhost:8080/cards/card/{cardId}
GET : http/localhost:8080/cards/account-cards/{accountId}
POST : http/localhost:8080/cards
PUT : http/localhost:8080/cards/{cardId}
DELETE : http/localhost:8080/cards/{cardId}


