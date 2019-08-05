# Account Transaction Sample Project

## Tech Stack:

	Spring Data REST - Hypermedia & domain model driven REST Webservices with HATEOAS.
	H2  - In Memory Relational Database.
	Swagger - API Documentation.
	Docker - Container for run application in isolated environment.
	Spock - Unit Test in an expressive ways.
	

## API Endpoints:

/api/accounts - Get all accounts

/api/accounts/{number} - Get an account

/api/accounts/{number}/transactions - Get all transactions of an account


## Execution Instruction:

1. Create Image

>	$> docker build --file=Dockerfile --tag=account-transaction:latest --rm=true .

2. Create a volume for mounting

>	$> docker volume create --name=account-transaction-config-repo

3. Run the contain from the image

>	$> docker run --name=account-server --publish=8080:8080 --volume=account-transaction-config-repo:/var/lib/account-transaction/config-repo account-transaction:latest



## Access info: 

> Application runs in default IP:port http://192.168.99.100:8080

> Swagger can be accessed http://192.168.99.100:8080/v2/api-docs
