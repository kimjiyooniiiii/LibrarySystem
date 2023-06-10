package com.exclaimation.librarysystem.service;

import com.exclaimation.librarysystem.dto.BookData;
import com.exclaimation.librarysystem.entity.Book;
import com.exclaimation.librarysystem.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository repository) {
        this.bookRepository = repository;
    }


    public Page<Book> search(String keyword, int page) {
        String title = keyword;
        String content = keyword;
        Pageable pageable = PageRequest.of(page, 10);
        Page<Book> books = bookRepository.findByKeyword(title, content, pageable);
        return books;
    }

    public BookData.response findBookDetail(long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(NullPointerException::new);
        return BookData.response.builder()
                .id(book.getBookId())
                .title(book.getTitle())
                .content(book.getContent())
                .image(book.getImage())
                .author(book.getAuthor())
                .build();

    }
}
