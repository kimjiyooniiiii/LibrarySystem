package com.exclaimation.librarysystem.repository;

import com.exclaimation.librarysystem.entity.Book;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    private final EntityManager em;

    public BookRepository(EntityManager em) {
        this.em = em;
    }

    public List<Book> findPageByTitleContainsOrContentContains(String title, String content, int page) {
        return em.createQuery("select b from Book b where b.title like concat('%' , :title , '%') or b.content like concat('%', :content, '%')", Book.class)
                .setParameter("title", title)
                .setParameter("content", content)
                .setFirstResult(page)
                .setMaxResults(10)
                .getResultList();


    }
}
