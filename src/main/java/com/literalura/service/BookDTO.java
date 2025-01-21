package com.literalura.service;

import com.literalura.model.Author;

import java.util.List;

public class BookDTO {
    private String title;
    private String language;
    private int downloads;
    private List<Author> authors;

    // Constructor sin argumentos (requerido por algunos frameworks como Jackson)
    public BookDTO() {
    }

    // Constructor completo (opcional, para facilitar inicializaciones)
    public BookDTO(String title, String language, int downloads, List<Author> authors) {
        this.title = title;
        this.language = language;
        this.downloads = downloads;
        this.authors = authors;
    }

    // Getters y Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    // Método toString para facilitar el depurado
    @Override
    public String toString() {
        return "BookDTO{" +
                "title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", downloads=" + downloads +
                ", authors=" + authors +
                '}';
    }
}
