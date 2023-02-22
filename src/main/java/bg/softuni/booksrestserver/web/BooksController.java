package bg.softuni.booksrestserver.web;

import bg.softuni.booksrestserver.model.dto.BookDto;
import bg.softuni.booksrestserver.model.entity.BookEntity;
import bg.softuni.booksrestserver.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") long bookId){
        Optional<BookDto> optBook = bookService.findBookById(bookId);

        return optBook.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<BookDto> deleteBookById(@PathVariable("id") long bookId){
        bookService.deleteById(bookId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto,
                                                 UriComponentsBuilder uriComponentsBuilder){
        long bookId = bookService.createBook(bookDto);

        return ResponseEntity
                .created(uriComponentsBuilder
                        .path("/api/books/{id}")
                        .build(bookId))
                .build();

    }
}
