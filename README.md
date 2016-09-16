# ShoppingCart
#README

This is a command line application based on Spring Boot technology. The idea behind is to buoild a a rogram able to calculayte
the price of some given products specified via command line arguments at application startup.
The use of Spring Boot has been decided  based on the fact that I could get easy access to a preconfigured spring application,
logging via logback, and command line argument API using ApplicationRunner  implementation.
Interesting as well would it be the capability to extends the program functionality ofering the sam eservice via http.

### Quick Start

###Specs
The application needs the follow spec:
1. Java JRE at least in version 1.8_x
2. Maven version 3.3.x
3. Spring Boot version 1.4

###Run it

The application can be built using via command line :

The application be run via maven in the console. The main command is :
1. mvn spring-boot:run from the folder where is located teh pom.xml file.
2. mvn package , instead will generate a jar file named ShoppingPricer-0.0.1-SNAPSHOT.jar

If you chose option 2 you will need then to use standard java command via command line as in the follow example :

java -jar ShoppingPricer-0.0.1-SNAPSHOT.jar -{Command to execute}

###Comands

The application at the moment can accept just 2 commands :
1. -Help
2. -PriceBasket

The first one will just print out the list of the available commands and arguments.

-PriceBasket
This command execute the price calculation for all the goods are passed as argument  via command line. Eg:
                    mvn spring-boot:run -PriceBasket Apple Apple
It will calculate the price of these two apples. Assuming an Apple cost 1 £ ,the expected result should be printed out on console like this :
Subtotal: £ 2
(No offers available)
Total price: £ 2

In case the system will reveal the presence of discounts and offers the Total price will change like this :
Subtotal: £ 2
Apples 10% off: -20p
Total: £ 1.80

###Comands definition

 Commands are defined as java Spring beans in the configuration. Each command it s annotated with a custom Annotation and a specific java interface
   that mark the bean as executable command:
   1. Custom annotation : WireOption
   2. Interface : Cmd

Al commands are placed in this package : com.bjss.shopping.cmd

The Command arguments are annotated as well with the same Aannotation but they implement a different interface : Product

During the phase of bena initialization the application scan these packages ooking for these kind of bean and build at rutime the
list of all available commands and product oin the store.


###RExtensibility : Command definition

   Command are defined as java Spring beans in the configuration. Each command it s annotated with a custom Annotation and a specific  java interface
   that mark the bean as command to execute and as well as possible argument via command line :
   1. Custom annotation : MLDWireOption
   2. Interface : MLDCommand

   The annotation set as well the name and he descrition of teh command that can be executed.
