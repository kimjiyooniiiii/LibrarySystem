package com.exclaimation.librarysystem.service;

import com.exclaimation.librarysystem.entity.RentEntity;
import com.exclaimation.librarysystem.repository.RentRepository;

import java.time.LocalDate;
import java.util.List;

public class RentService {

    private final RentRepository rentRepository;

    public RentService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    public int rent(Long book_id, String student_id){

        List<RentEntity> re = rentRepository.findByBookId(book_id);
        if(re.size() != 0){
            System.out.println("대출자가 이미 존재합니다");
            return -1;
        }
        RentEntity rentEntity = new RentEntity();
        rentEntity.setBook_id(book_id);
        rentEntity.setStudent_id(student_id);
        rentEntity.setRent_dt(LocalDate.now());
        rentEntity.setReturn_dt(LocalDate.now().plusDays(14));
//        rentEntity.setSpare_dt();
        rentEntity.set_return(false);
        rentEntity.set_continue(false);

        rentRepository.save(rentEntity);
        System.out.println("정상적으로 도서를 대출하였습니다");
        return 0;
    }

    public int returnBook(Long book_id){

        List<RentEntity> re = rentRepository.findByBookId(book_id);
        if(re.size() == 0){
            System.out.println("반납할 수 없는 도서입니다.");
            return -1;
        }

        rentRepository.deleteById(re.get(0).getRent_id());
        System.out.println("정상적으로 반납되었습니다");
        return 0;
    }
}
