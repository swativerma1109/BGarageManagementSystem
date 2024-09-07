![image](https://github.com/user-attachments/assets/f1057c17-fcd9-4fb0-9cde-468913fd2ac5)



**Order Service**
The Order Service is responsible for managing orders. It interacts with the Inventory Service to check the availability of parts before placing an order. The Order Service uses MySQL as its database.
API Endpoints:
POST /api/order/create/{supplier}: Creates a new order for provided supplier.
Example Request:
URL : http://localhost:8082/orderservice/api/order/create/Supplier-A

Body : {"skucode": "Part-1", "quantity": 2,"price": 123.45, "supplier": "Supplier-A"}


**Inventory Service**
The Inventory Service manages the inventory of products. It communicates with the Product Service to retrieve product information and uses MySQL as its database
API Endpoints:
GET /api/inventory/{productId} : Retrieves the stock size of a particular product.
Example Request:

URL: http://localhost:8082/inventoryservice/api/inventory

Body:

{
       "skuCode": "Part3",
       "quantity": 100,
       "supplier": "Supplier-B",
       "minOrderQuantity": 10,
       "thresholdLimit": 5
   }


**System Architecture**
The system is designed using a microservices architecture pattern, where each microservice is responsible for a specific domain. 
The microservices communicate with each other using synchronous service-to-service calls.

**Database Setup**
Both the Inventory Service and Order Service use MySQL as their databases. Set up MySQL databases for these services and configure the connection details in their respective configuration files.

**API Gateway Setup**
An API Gateway is used to handle client requests and provide authentication and authorization. Keycloak is integrated with the API Gateway for this purpose.
Set up Keycloak and configure the API Gateway to use Keycloak for authentication and authorization.

**Service Discovery**
Netflix Eureka is used as the service discovery server. It enables each microservice to register itself with Eureka, which allows other services to discover and communicate with it.
Set up and configure Netflix Eureka for service discovery.
Service Discovery
Netflix Eureka is used as the service discovery server. It enables each microservice to register itself with Eureka, which allows other services to discover and communicate with it.
Set up and configure Netflix Eureka for service discovery.


