## TICKET APP :rocket:

In Ticket App , airport , airline , route , flight and ticket can be created.

User can search flight by using flight id , route id and airline id. 

User can check available seat in flight.

The price changes according to the remaining seat as showed in below.

->When ticket is sold more than %10 , price also increases by %10. 

->When ticket is sold more than %20 , price also increases by %20.

In order the test application endpoints , Swagger-ui can be used with clicked below link.
![image](https://user-images.githubusercontent.com/32959131/78574218-59e37c00-7832-11ea-93fc-b05ee03e9ce6.png)

http://localhost:8080/swagger-ui.html#

There are 5 controller and you can find all enpoints under these tittles. 
![image](https://user-images.githubusercontent.com/32959131/78561400-4d562800-7820-11ea-8c40-55177821860d.png)
![image](https://user-images.githubusercontent.com/32959131/78551504-838aac00-780e-11ea-8dfe-de61479468fc.png)
![image](https://user-images.githubusercontent.com/32959131/78561219-02d4ab80-7820-11ea-9dd5-63f2ed646b44.png)

**Some Basic Steps to buy ticket**

- [ ] - Create Airline
![image](https://user-images.githubusercontent.com/32959131/78569123-cb6bfc00-782b-11ea-8eca-a14ffc461150.png)

- [ ] - Create an Airport
![image](https://user-images.githubusercontent.com/32959131/78562447-079a5f00-7822-11ea-8da5-5a01fbb0a831.png)

- [ ] - Get All Airport
![image](https://user-images.githubusercontent.com/32959131/78562736-82637a00-7822-11ea-82a1-b73d099e6097.png)

- [ ] - Create a Route : Before creating route departure airport id and destination airport id must be known.Both of them can be taken with getAllAirport . (as showed in above screenhot)
![image](https://user-images.githubusercontent.com/32959131/78563049-061d6680-7823-11ea-8a5a-cd1ca46a1ac1.png)

- [ ] - Get All Route : Route id will be used when flight is created. Each route has own id. 
![image](https://user-images.githubusercontent.com/32959131/78565480-a923af80-7826-11ea-9472-464e16cbc5c5.png)

- [ ] Create Flight : Before creating flight, airline id and route id should be knowm. They can be taken with getAllAirline and getAllRoute 
![image](https://user-images.githubusercontent.com/32959131/78567933-11c05b80-782a-11ea-84ac-ad1e2b75622b.png)

- [ ] Get All Flight
![image](https://user-images.githubusercontent.com/32959131/78567065-c9ed0480-7828-11ea-9d25-8166a8553097.png)

- [ ] Buy Ticket : Before buy ticket, flight id must be known.It can be taken from with
![image](https://user-images.githubusercontent.com/32959131/78568640-120d2680-782b-11ea-88f8-9c1e1c1abcbf.png)

- [ ] Cancel Ticket : Ticket can be canceled by given a ticket id.
![image](https://user-images.githubusercontent.com/32959131/78568741-3832c680-782b-11ea-8745-cdf3398b636e.png)

