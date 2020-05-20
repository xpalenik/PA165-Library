# PA165-Library

See our GitHub wiki at https://github.com/petr7555/PA165-Library/wiki

To start the web application use following commands:
- start with cloning this project with `git clone https://github.com/xpalenik/PA165-Library.git`
- `cd /your/path/to/project/PA165-Library`
- `mvn clean install`
- `cd library-rest`
- `mvn spring-boot:run`
- in your browser go to "http://localhost:8080/pa165"
- to log in use email and password one of the added librarian:
        - 'kHermano@mail.com', password: 'kHermanoPass'
        - 'mPalenik@mail.com', password: 'mPalenikPass'
        
        
To test REST:
- make sure your application is running
- in your command line run one of these commands to store credentials:
    `curl -v -F username=kHermano@mail.com -F password=kHermanoPass --cookie-jar /tmp/cookie http://localhost:8080/pa165/login`
    or
    `curl -v -F username=mPalenik@mail.com -F password=mPalenikPass --cookie-jar /tmp/cookie http://localhost:8080/pa165/login`
- run :
    - to get all the books:
        `curl -i --header "Accept:application/json" -X GET -b /tmp/cookie http://localhost:8080/pa165/rest/books`
    - to get all books which have title containing "Witcher":
        `curl -i --header "Accept:application/json" -X GET -b /tmp/cookie http://localhost:8080/pa165/rest/books_title/Witcher`
    - to get all books which have author containing "Andrzej":
        `curl -i --header "Accept:application/json" -X GET -b /tmp/cookie http://localhost:8080/pa165/rest/books_author/Andrzej`
    - to get book with id=1:
        `curl -i --header "Accept:application/json" -X GET -b /tmp/cookie http://localhost:8080/pa165/rest/book_id/1`
    