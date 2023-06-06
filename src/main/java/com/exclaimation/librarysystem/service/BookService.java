package com.exclaimation.librarysystem.service;

import com.exclaimation.librarysystem.dto.BookData;
import com.exclaimation.librarysystem.entity.Book;
import com.exclaimation.librarysystem.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository repository) {
        this.bookRepository = repository;
    }

    public List<BookData.response> search(String keyword, Integer page) {
        String title = keyword ;
        String content = keyword ;
        List<Book> books = bookRepository.findPageByTitleContainsOrContentContains(title ,content,page);
        List<BookData.response> responses = new ArrayList<>();
        for(Book book :books){
            responses.add(BookData.response.builder()
                    .id(book.getBookId())
                    .title(book.getTitle())
                    .content(book.getContent())
                    .available(book.isRent()?"대출 가능" : "대출 불가능")
                    .build());
        }
        return responses;
    }
}
