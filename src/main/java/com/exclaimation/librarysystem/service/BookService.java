package com.exclaimation.librarysystem.service;

import com.exclaimation.librarysystem.dto.BookData;
import com.exclaimation.librarysystem.entity.Book;
import com.exclaimation.librarysystem.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository repository) {
        this.bookRepository = repository;
    }

//    public List<BookData.response> search(String keyword, Pageable page, int max) {
//        String title = keyword;
//        String content = keyword;
//
//        List<Book> books = bookRepository.findKeyword(title, content, page);
//        List<BookData.response> responses = new ArrayList<>();
//        for (Book book : books) {
//            responses.add(BookData.response.builder()
//                    .id(book.getBookId())
//                    .image(book.getImage())
//                    .title(book.getTitle())
//                    .content(book.getContent())
//                    .available(book.isRent() ? "대출 불가능" : "대출 가능")
//                    .build());
//        }
//        return responses;
//    }

    public Page<Book> search(String keyword, int page) {
        String title = keyword;
        String content = keyword;
        Pageable pageable = PageRequest.of(page, 10);
        Page<Book> books = bookRepository.findByKeyword(title, content, pageable);
        return books;
    }

}
