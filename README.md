# spring-boot-h2
Prerequisites - 
1. Install STS or Spring boot. You can also use other IDEs. It should support Maven projects.
2. Make sure Lombok and JUnit are running. You may need to add the JARS to the classpath


# Notes: 
1. To start the application,you need to run SpringBootH2Application.java. The application runs on port 8080.
2. The application uses H2 in-memory DB. The database URL will be printed in the logs. 
Sample log - "H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:48e5427c-80a6-4bf6-a3e5-19ee760a0ba1'"
3. You can access the H2-console in the browser by invoking the URL http://localhost:8080/h2-console. 
4. In the JDBC URL, specify the URL obtained in the step2 - "jdbc:h2:mem...........". Leave other things unchanged and click 'Connect.'

#APIs - curl
1. Create an account - 
curl --location --request POST 'http://localhost:8080/transactionRoutine/v1/accounts' \
--header 'Content-Type: application/json' \
--data-raw '{
   "document_number": 12345678900
}'   
Sample response : 
{
    "account_id": 1,
    "document_number": "12345678900"
}
2. Create a transaction - 
curl --location --request POST 'http://localhost:8080/transactionRoutine/v1/transactions' \
--header 'Content-Type: application/json' \
--data-raw '{
"account_id": 1,
"operation_type_id": 3,
"amount": -124.45
}'  
Sample response :
{
    "amount": -124.45,
    "eventDate": "2022-11-08T20:59:38.436+00:00",
    "transaction_id": 2,
    "account_id": 1,
    "operation_type_id": 3
}
3. Get Account by id - 
curl --location --request GET 'http://localhost:8080/transactionRoutine/v1/accounts/1' \
--header 'Content-Type: application/json' \
--data-raw '{
   "document_number": 12345678900
}'   
Sample response : 
{
    "account_id": 1,
    "document_number": "12345678900"
}
