Account Transaction

Tech Stack:

	Spring Data REST - Hypermedia & domain model driven REST Webservices with HATEOAS.
	H2 - In Memory Relational Database.
	Swagger - API Documentation.
	Dockerizing - Container for run application in isolated environment.
	Spock Unit Test - For expressive ways of testing.
	

Database model:






SQL script is embedded to create table and pre-populate data in to the table.



API Endpoints:




Instructions:

1. Create Image

	$> docker build --file=Dockerfile --tag=account-transaction:latest --rm=true .

2. Create a volume for mounting

	$> docker volume create --name=account-transaction-config-repo

3. Run the contain from the image

	$> docker run --name=account-server --publish=8080:8080 --volume=account-transaction-config-repo:/var/lib/account-transaction/config-repo account-transaction:latest



Access info: 

Application runs in default IP:port http://192.168.99.100:8080

Swagger can be accessed http://192.168.99.100:8080/v2/api-docs
