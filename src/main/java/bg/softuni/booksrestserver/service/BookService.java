package bg.softuni.booksrestserver.service;


import bg.softuni.booksrestserver.model.dto.AuthorDto;
import bg.softuni.booksrestserver.model.dto.BookDto;
import bg.softuni.booksrestserver.model.entity.AuthorEntity;
import bg.softuni.booksrestserver.model.entity.BookEntity;
import bg.softuni.booksrestserver.repository.AuthorRepository;
import bg.softuni.booksrestserver.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<BookDto> getAllBooks() {

        return bookRepository.findAll().stream()
                .map(this::createBookDto)
                .collect(Collectors.toList());
    }

    public Optional<BookDto> findBookById(long bookId) {
        return bookRepository.findById(bookId)
                .map(this::createBookDto);
    }

    public void deleteById(long bookId) {
        bookRepository.deleteById(bookId);
    }

    public long createBook(BookDto bookDto) {

        String authorName = bookDto.getAuthorName().getName();
        Optional<AuthorEntity> optAuthor = authorRepository.findByName(authorName);

        BookEntity bookEntity = new BookEntity()
                .setIsbn(bookDto.getIsbn())
                .setTitle(bookDto.getTitle())
                .setAuthor(optAuthor.orElseGet(() -> createNewAuthor(authorName)));

        return bookRepository.save(bookEntity).getId();
    }

    private AuthorEntity createNewAuthor(String authorName) {
        return authorRepository.save(new AuthorEntity().setName(authorName));
    }

    private BookDto createBookDto(BookEntity bookEntity) {
        return new BookDto()
                .setName(bookEntity.getTitle())
                .setId(bookEntity.getId())
                .setIsbn(bookEntity.getIsbn())
                .setAuthorName(new AuthorDto()
                        .setName(bookEntity.getAuthor().getName()));
    }

}
