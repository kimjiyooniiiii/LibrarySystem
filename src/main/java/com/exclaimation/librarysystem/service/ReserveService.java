package com.exclaimation.librarysystem.service;

import com.exclaimation.librarysystem.entity.Book;
import com.exclaimation.librarysystem.entity.ReserveEntity;
import com.exclaimation.librarysystem.repository.BookRepository;
import com.exclaimation.librarysystem.repository.ReserveRepository;

import java.time.LocalDate;
import java.util.*;

public class ReserveService {

    private final ReserveRepository reserveRepository;
    private final BookRepository bookRepository;

    public ReserveService(ReserveRepository reserveRepository, BookRepository bookRepository) {
        this.reserveRepository = reserveRepository;
        this.bookRepository = bookRepository;
    }

    public void makeReservation(Long book_id, String student_id){

        List<ReserveEntity> list = reserveRepository.findByBookId(book_id);
        if( list.size() >= 3){
            System.out.println("예약자가 이미 3명이 존재하여 더이상 예약을 할 수 없습니다.");
            return;
        }

        Book book = bookRepository.findById(book_id).orElseThrow();

        ReserveEntity reserveEntity = new ReserveEntity();
        reserveEntity.setBook_id(book_id);
        reserveEntity.setBook_name(book.getTitle());
        reserveEntity.setStudent_id(student_id);
        reserveEntity.setReservation_date(LocalDate.now());
        reserveRepository.save(reserveEntity);
        System.out.println("정상적으로 도서를 예약했습니다");
    }

    // delete
    public boolean checkReservationIdByBookId(Long book_id, String studentId){
        List<ReserveEntity> list = reserveRepository.findByBookId(book_id);
        if(0!=list.size()){
            Optional<ReserveEntity> reserve = reserveRepository.findByBookIdAndStudentId(book_id, studentId);
            if(reserve.isEmpty()){
                return true;
            }
            else{
                ReserveEntity entity = reserve.get();
                reserveRepository.deleteById(entity.getReservation_id());
                return false;
            }
        }else{
            return false;
        }
    }

    public ReserveEntity findById(Long reservation_id) throws IllegalAccessException {
        return reserveRepository.findById(reservation_id).orElseThrow(
                IllegalAccessException::new
        );
    }

    public int deleteById(Long reservation_id){
        reserveRepository.deleteById(reservation_id);
        return 0;
    }
}
