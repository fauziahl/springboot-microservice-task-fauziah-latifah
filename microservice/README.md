# Spring Boot CRUD Books API

### Tech Stack
* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven

### API Endpoints
* GET 		/api/books 	    (Get all books)
* GET 		/api/books/{id} (Get book by ID)
* POST		/api/books	    (Create new book)
* PUT		/api/books/{id}	(Update full data of book)
* PATCH		/api/books/{id}	(Update partial data of book)
* DELETE	/api/books/{id}	(Delete book)

### Environment Variables
DB_HOST	Host database	    localhost
DB_PORT	Port database	    3306
DB_NAME	Nama database	    books_db
DB_USER	Username database	root
DB_PASS	Password database	password