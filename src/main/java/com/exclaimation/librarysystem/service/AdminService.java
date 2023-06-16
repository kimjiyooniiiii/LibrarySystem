package com.exclaimation.librarysystem.service;

import com.exclaimation.librarysystem.entity.Book;
import com.exclaimation.librarysystem.entity.RentEntity;
import com.exclaimation.librarysystem.entity.Require;
import com.exclaimation.librarysystem.entity.ReserveEntity;
import com.exclaimation.librarysystem.repository.BookRepository;
import com.exclaimation.librarysystem.repository.RentRepository;
import com.exclaimation.librarysystem.repository.RequireRepository;
import com.exclaimation.librarysystem.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private final RentRepository rentRepository;

    @Autowired
    private final RequireRepository requireRepository;

    @Autowired
    private final ReserveRepository reserveRepository;

    private final BookRepository bookRepository;

    public AdminService(RentRepository repository, RequireRepository requireRepository, ReserveRepository reserveRepository, BookRepository bookRepository) {
        this.rentRepository = repository;
        this.requireRepository = requireRepository;
        this.reserveRepository = reserveRepository;
        this.bookRepository = bookRepository;
    }

    // 회원들 대출 목록 보기
    public List<RentEntity> showRentList() {
        List<RentEntity> rentList = rentRepository.findAll();
        // 반납 안된 목록만 보기
        List<RentEntity> nowRentList = new ArrayList<>();
        for(int i = 0; i < rentList.size(); i++){
            if(!rentList.get(i).is_return())
                nowRentList.add(rentList.get(i));
        }
        return nowRentList;
    }

    // 희망 도서 목록 보기
    public List<Require> showRequireList() {
        List<Require> requireList = requireRepository.findAll();

        return requireList;
    }

    // 예약 도서 목록 보기
    public List<ReserveEntity> showReserveList() {
        List<ReserveEntity> reserveList = reserveRepository.findAll();

        return  reserveList;
    }

    //사용자 도서 연장
    public void renewRent(Long rentId, PrintWriter out) {
        Optional<RentEntity> entity = rentRepository.findById(rentId);

        if(entity.isEmpty()){
            out.println("<script>alert('연장할 수 없는 도서입니다'); window.location.href='/admin/rentList';</script> ");
            out.flush();
            System.out.println("연장할 수 없는 도서입니다.");
        }
        else{
            RentEntity rent = entity.get();
            long bookId = rent.getBook_id();
            Optional<Book> bookEntity = bookRepository.findById(bookId);

            //예약한 사람이 있거나, 이미 연체가 되었다면 '연장불가'
            if((bookEntity.isPresent() && bookEntity.get().isReserve())
                    || (rent.getSpare_dt() < 0)
                    || (rent.is_continue() == true)){
                out.println("<script>alert('연장할 수 없는 도서입니다'); window.location.href='/admin/rentList';</script> ");
                out.flush();
                System.out.println("연장할 수 없는 도서입니다.");
            }

            else{
                rent.setReturn_dt(rent.getReturn_dt().plusDays(7));   //7일 연장
                rent.set_continue(true);
                rent.setSpare_dt(rent.getSpare_dt() + 7);
                rentRepository.save(rent);
                out.println("<script>alert('도서를 연장하였습니다.');  window.location.href='/admin/rentList';</script> ");
                out.flush();
            }
        }
    }
}
