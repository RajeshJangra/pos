# Point Of Sales

Description:
    To run the project 'mvn spring-boot:run'.
    
Github:
    Project is checked in a public repository. Please clone on your desktop.
    SSH: git@github.com:RajeshXebia/pos.git
    HTTPS: https://github.com/RajeshXebia/pos.git
    
Technology:
    Spring Boot, Java 8, Hibernate, Junit, Mockito, H2
    
Data Model
    Territory: Information about the territory.
    
Startup data:
   It will create data from ApplicationStartup class. it will create three categories, three territories, three products, a cart and 10 bills(to check pagination ) for the same cart.
    
Unit Test cases:
    There are 73 test cases. Mainly for Service and Controller classes (100% Coverage). There is a single test case for Bill entity.
    
Use cases:
    
    1. http://localhost:8080/bills?pageNumber=1&sortField=billDate&sortDirection=DESC&totalBillAmount=16700
        To view details of bill id 1, Page no 1 (pages start from 0), sorted by billDate in descending order with bill amount equal to 16700. Page size is 3. sorting and filtering can be done with billDate, locationCode, totalBillAmount fields.
    
    2. http://localhost:8080/carts/1/addProduct/1
        To add product with id to cart with id 1
        
    3. http://localhost:8080/carts/1/removeProduct/1
        To remove product with id from cart with id 1
        
    4. http://localhost:8080/carts/1/updateCount/1?itemCount=5
        To update count of product with id 1 to 5
        
    5. I have also created CRU (No delete) operations for Category, Product and Territory entities as well.
    
    