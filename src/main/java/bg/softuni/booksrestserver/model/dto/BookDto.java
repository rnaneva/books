package bg.softuni.booksrestserver.model.dto;

public class BookDto {

    private String title;
    private long id;
    private String isbn;
    private AuthorDto authorName;

    public String getTitle() {
        return title;
    }

    public BookDto setName(String name) {
        this.title = name;
        return this;
    }

    public long getId() {
        return id;
    }

    public BookDto setId(long id) {
        this.id = id;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookDto setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public AuthorDto getAuthorName() {
        return authorName;
    }

    public BookDto setAuthorName(AuthorDto authorName) {
        this.authorName = authorName;
        return this;
    }
}
