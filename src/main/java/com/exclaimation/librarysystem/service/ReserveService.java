package com.exclaimation.librarysystem.service;

import com.exclaimation.librarysystem.domain.Seat;
import com.exclaimation.librarysystem.entity.ReserveEntity;
import com.exclaimation.librarysystem.entity.SeatEntity;
import com.exclaimation.librarysystem.repository.ReserveRepository;
import com.exclaimation.librarysystem.repository.SeatRepository;

import java.time.LocalDate;
import java.util.*;

public class ReserveService {

    private final ReserveRepository reserveRepository;

    public ReserveService(ReserveRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }

    public void makeReservation(Long book_id, String student_id){

        List<ReserveEntity> list = reserveRepository.findByBookId(book_id);
        if( list.size() >= 3){
            System.out.println("예약자가 이미 3명이 존재하여 더이상 예약을 할 수 없습니다.");
            return;
        }

        ReserveEntity reserveEntity = new ReserveEntity();
        reserveEntity.setBook_id(book_id);
        reserveEntity.setStudent_id(student_id);
        reserveEntity.setReservation_date(LocalDate.now());
        reserveRepository.save(reserveEntity);
        System.out.println("정상적으로 도서를 예약했습니다");
    }

    // delete
    public Long fastReservationIdByBookId(Long book_id){
        List<ReserveEntity> list = reserveRepository.findByBookId(book_id);
        if(list.size() == 0)
        {
            System.out.println("예약자가 존재하지 않습니다.");
            return 0l;
        }

        Collections.sort(list, (ReserveEntity a, ReserveEntity b) ->
            a.getReservation_date().compareTo(b.getReservation_date())
                );

        // 날짜가 가장 빠른 예약
        return list.get(0).getReservation_id();
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
