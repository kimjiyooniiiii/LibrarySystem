package com.exclaimation.librarysystem.repository;

import com.exclaimation.librarysystem.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    private final EntityManager em;

    public BookRepository(EntityManager em) {
        this.em = em;
    }

    public List<Book> findKeyword(String title, String content, Pageable page) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.title like concat('%' , :title , '%') or b.content like concat('%', :content, '%')", Book.class)
                .setParameter("title", title)
                .setParameter("content", content);
//                .setFirstResult(page.getPageNumber())
//                .setMaxResults(10);

        int total = query.getResultList().size();
        query.setFirstResult((int) page.getOffset());
        query.setMaxResults(page.getPageSize());

        return query.getResultList();



    }
    public int getTotal(String title, String content, Pageable page){
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.title like concat('%' , :title , '%') or b.content like concat('%', :content, '%')", Book.class)
                .setParameter("title", title)
                .setParameter("content", content);

        return query.getResultList().size();
    }
}
