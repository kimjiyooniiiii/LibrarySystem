package com.exclaimation.librarysystem.service;

import com.exclaimation.librarysystem.domain.Seat;
import com.exclaimation.librarysystem.entity.SeatEntity;
import com.exclaimation.librarysystem.repository.SeatRepository;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class SeatService {
    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat.Simple> findSeats(){
        List<Seat.Simple> list = new ArrayList<>();
        for(SeatEntity seatEntity : seatRepository.findAll()){
            Seat.Simple seat = new Seat.Simple();
            seat.setSeat_id(seatEntity.getSeat_id());
            seat.setStudent_id(seatEntity.getStudent_id());
            seat.setEnable((seatEntity.isEnable()));

            list.add(seat);
        }

        return list;
    }

    public SeatEntity getSeatById(Long SeatId){
        return seatRepository.findById(SeatId).orElseThrow(
                IllegalArgumentException::new
        );
    }

    public void updateSeat(Seat.Simple seatForm){

        SeatEntity seatEntity = seatRepository.findById(seatForm.getSeat_id()).orElseThrow(
                IllegalArgumentException::new
        );
        seatEntity.setStudent_id(seatForm.getStudent_id());
        seatEntity.setEnable(seatForm.isEnable()^true);

        seatRepository.save(seatEntity);
    }
}
