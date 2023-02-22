package bg.softuni.booksrestserver.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String isbn;

    @ManyToOne
    private AuthorEntity author;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public BookEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookEntity setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public BookEntity setAuthor(AuthorEntity author) {
        this.author = author;
        return this;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author=" + (author != null ? getAuthor() : null) +
                '}';
    }
}
