package com.literalura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class Principal implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int option;

        try {
            do {
                System.out.println("1 - Buscar libro por título");
                System.out.println("2 - Listar libros registrados");
                System.out.println("3 - Listar autores registrados");
                System.out.println("4 - Listar autores vivos en determinado año");
                System.out.println("5 - Listar libros por idioma");
                System.out.println("0 - Salir");

                System.out.print("Seleccione una opción: ");
                option = scanner.nextInt();
                scanner.nextLine();  // Consume el salto de línea

                switch (option) {
                    case 1:
                        System.out.print("Ingrese el título del libro: ");
                        String title = scanner.nextLine();
                        bookService.searchBookByTitle(title);
                        System.out.println("Búsqueda completada.");
                        break;
                    case 2:
                        System.out.println("Libros registrados:");
                        bookService.listAllBooks().forEach(book -> System.out.println(book));
                        break;
                    case 3:
                        System.out.println("Autores registrados:");
                        authorService.getAllAuthors().forEach(author -> System.out.println(author));
                        break;
                    case 4:
                        System.out.print("Ingrese el año: ");
                        int year = scanner.nextInt();
                        System.out.println("Autores vivos en el año " + year + ":");
                        authorService.getAliveAuthorsInYear(year).forEach(author -> System.out.println(author));
                        break;
                    case 5:
                        System.out.print("Ingrese el idioma: ");
                        String language = scanner.nextLine();
                        System.out.println("Libros en el idioma " + language + ":");
                        bookService.listBooksByLanguage(language).forEach(book -> System.out.println(book));
                        break;
                    case 0:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } while (option != 0);
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada inválida. Por favor ingrese un número.");
        } finally {
            scanner.close();
        }
    }
}

