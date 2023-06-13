package com.exclaimation.librarysystem.service;

import com.exclaimation.librarysystem.entity.Book;
import com.exclaimation.librarysystem.entity.RentEntity;
import com.exclaimation.librarysystem.repository.BookRepository;
import com.exclaimation.librarysystem.repository.RentRepository;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RentService {

    private final RentRepository rentRepository;
    private final BookRepository bookRepository;
    public RentService(RentRepository rentRepository, BookRepository bookRepository) {
        this.rentRepository = rentRepository;
        this.bookRepository = bookRepository;
    }

    public boolean isRent(Long book_id, String student_id){
        List<RentEntity> re = rentRepository.findByBookId(book_id);
        if(re.size() != 0){
            System.out.println("대출자가 이미 존재합니다");
            return true;
        }
        return false;
    }
    public void rent(Long book_id, String student_id){
        RentEntity rentEntity = new RentEntity();
        rentEntity.setBook_id(book_id);
        rentEntity.setStudent_id(student_id);
        rentEntity.setRent_dt(LocalDate.now());
        rentEntity.setReturn_dt(LocalDate.now().plusDays(14));
//        rentEntity.setSpare_dt();
        rentEntity.set_return(false);
        rentEntity.set_continue(false);
        rentEntity.setBook_name(bookRepository.findById(book_id).get().getTitle());
        Optional<Book> entity = bookRepository.findById(book_id);
        if(entity.isPresent()){
            Book book = entity.get();
            book.setRent(true);
            bookRepository.save(book);
        }
        rentRepository.save(rentEntity);
    }

    public void returnBook(Long rentId, PrintWriter out)
    {

        List<RentEntity> entity = rentRepository.findByBookId(rentId);
        if(entity.size() == 0){
            out.println("<script>alert('반납할 수 없는 도서입니다'); window.close();</script> ");
            out.flush();
            System.out.println("반납할 수 없는 도서입니다.");
        }
        else{
            RentEntity rent = entity.get(0);
            rent.set_return(true);
            long bookId = rent.getBook_id();
            Optional<Book> bookEntity = bookRepository.findById(bookId);
            if(bookEntity.isPresent()){
                Book book = bookEntity.get();
                book.setRent(false);
                bookRepository.save(book);
            }
            rentRepository.save(rent);
            out.println("<script>alert('도서를 반납하였습니다.'); window.location.href='/admin/rentList';</script> ");
            out.flush();
        }
    }

    public int getRentBookCnt(String student_id){
        List<RentEntity> re = rentRepository.findByStudentId(student_id);

        int cnt = 0;
        for(int i = 0; i < re.size(); i++){
            LocalDate now = LocalDate.now();
            LocalDate returnDate = re.get(i).getReturn_dt();

            if(now.compareTo(returnDate) <= 0)
                cnt += 1;
        }

        return cnt;
    }

    public int getDelayBookCnt(String student_id){
        List<RentEntity> re = rentRepository.findByStudentId(student_id);

        int cnt = 0;
        for(int i = 0; i < re.size(); i++){
            LocalDate now = LocalDate.now();
            LocalDate returnDate = re.get(i).getReturn_dt();

            if(now.compareTo(returnDate) > 0)
                cnt += 1;
        }

        return cnt;
    }

    public void returnRent(Long rentId, PrintWriter out) {
        Optional<RentEntity> entity = rentRepository.findById(rentId);
        if(entity.isEmpty()){
            out.println("<script>alert('반납할 수 없는 도서입니다'); window.close();</script> ");
            out.flush();
            System.out.println("반납할 수 없는 도서입니다.");
        }
        else{
            RentEntity rent = entity.get();
            rent.set_return(true);
            long bookId = rent.getBook_id();
            Optional<Book> bookEntity = bookRepository.findById(bookId);
            if(bookEntity.isPresent()){
                Book book = bookEntity.get();
                book.setRent(false);
                bookRepository.save(book);
            }
            rentRepository.save(rent);
            out.println("<script>alert('도서를 반납하였습니다.'); window.location.href='/admin/rentList';</script> ");
            out.flush();
        }
    }
}
