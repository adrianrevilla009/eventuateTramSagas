# eventuateTramSagas
Eventuate tram framework for saga management

Pasos a seguir para ejecutar los test

Test 1: Pedido correcto

1. Creamos un customer
$ curl -X POST --header "Content-Type: application/json" -d '{
   "creditLimit": {
   "amount": 5
   },
   "name": "Pepe"
}' http://localhost:8082/customers

2. Creamos un producto
$ curl -X POST --header "Content-Type: application/json" -d '{
   "name": "Leche",
   "description": "Entera",
   "stock": 10
}' http://localhost:8084/product

3. Hacemos un pedido
$ curl -X POST --header "Content-Type: application/json" -d '{
   "customerId": 1,
   "orderTotal": {
   "amount": 4
   },
   "productList": [
      {"id": 1, "name": "Leche", "description": "Entera", "stock": 5}
   ]
}' http://localhost:8081/orders

Test 2: Pedido rechazado porque falta el saldo del cliente
1. Creamos un customer
$ curl -X POST --header "Content-Type: application/json" -d '{
   "creditLimit": {
   "amount": 5
   },
   "name": "Juan"
}' http://localhost:8082/customers

2. Creamos un producto
$ curl -X POST --header "Content-Type: application/json" -d '{
   "name": "Yogurt",
   "description": "Limon",
   "stock": 10
}' http://localhost:8084/product

3. Hacemos un pedido
$ curl -X POST --header "Content-Type: application/json" -d '{
   "customerId": 2,
   "orderTotal": {
   "amount": 8
   },
   "productList": [
   {"id": 2, "name": "Yogurt", "description": "Limon", "stock": 5}
   ]
}' http://localhost:8081/orders

Test 3: Pedido rechazado porque falta el stock de producto
1. Creamos un customer
   $ curl -X POST --header "Content-Type: application/json" -d '{
   "creditLimit": {
   "amount": 5
   },
   "name": "Pedro"
   }' http://localhost:8082/customers

2. Creamos un producto
   $ curl -X POST --header "Content-Type: application/json" -d '{
   "name": "Galleta",
   "description": "Chocolate",
   "stock": 10
   }' http://localhost:8084/product

3. Hacemos un pedido
   $ curl -X POST --header "Content-Type: application/json" -d '{
   "customerId": 3,
   "orderTotal": {
   "amount": 3
   },
   "productList": [
   {"id": 3, "name": "Galleta", "description": "Chocolate", "stock": 20}
   ]
   }' http://localhost:8081/orders
