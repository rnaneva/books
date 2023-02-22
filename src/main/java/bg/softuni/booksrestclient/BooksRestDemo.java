package bg.softuni.booksrestclient;


import bg.softuni.booksrestclient.model.dto.BookDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BooksRestDemo implements CommandLineRunner {

    private final RestTemplate restTemplate;
    private final static String API_URL = "http://localhost:8080/api/books";
    private final static Logger LOGGER = LoggerFactory.getLogger(BooksRestDemo.API_URL);

    public BooksRestDemo(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public void run(String... args) throws Exception {

        ResponseEntity<BookDto[]> booksResponse =
                restTemplate.getForEntity(API_URL, BookDto[].class);

        if(booksResponse.hasBody()){
            BookDto[] books = booksResponse.getBody();

            for (BookDto aBook : books) {
                LOGGER.info("Retrieved a book: {}", aBook);

            }
        }

    }
}
