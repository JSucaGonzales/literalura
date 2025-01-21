package com.literalura.service;

import com.literalura.model.Author;
import com.literalura.model.Book;
import com.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    private final String API_URL = "https://gutendex.com/books?search=";

    public void searchBookByTitle(String title) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + title;

        // Realizamos la solicitud a la API externa
        BookDTO[] books = restTemplate.getForObject(url, BookDTO[].class);

        if (books != null) {
            for (BookDTO bookDto : books) {
                // Convertimos el DTO en entidad y guardamos
                Book book = convertToEntity(bookDto);
                bookRepository.save(book);
            }
        }
    }

    public List<Book> listAllBooks() {
        // Retorna todos los libros de la base de datos
        return bookRepository.findAll();
    }

    public List<Book> listBooksByLanguage(String language) {
        // Busca libros por idioma en la base de datos
        return bookRepository.findByLanguage(language);
    }

    private Book convertToEntity(BookDTO bookDto) {
        // Convertimos el DTO recibido de la API en una entidad Book
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setLanguage(bookDto.getLanguage());
        book.setDownloads(bookDto.getDownloads());

        // Procesamos la lista de autores
        List<Author> authors = bookDto.getAuthors().stream()
                .map(author -> authorService.findOrCreateAuthor(author.getName()))
                .collect(Collectors.toList());

        book.setAuthors(authors);
        return book;
    }
}

