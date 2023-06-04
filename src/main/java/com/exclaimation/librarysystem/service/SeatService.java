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
            seat.setSeatId(seatEntity.getSeatId());
            seat.setUserId(seatEntity.getUserId());
            seat.setUsed(seatEntity.isUsed());
            list.add(seat);
        }

        return list;
    }

    public SeatEntity getSeatById(Long SeatId){
        return seatRepository.findById(SeatId).orElseThrow(
                IllegalArgumentException::new
        );
    }

    public Long updateSeat(Seat.Simple seatForm){

        SeatEntity seatEntity = seatRepository.findById(seatForm.getSeatId()).orElseThrow(
                IllegalArgumentException::new
        );
        seatEntity.setUserId(seatForm.getUserId());
        seatEntity.setUsed(seatForm.isUsed()^true);

        seatRepository.save(seatEntity);
        return  seatEntity.getSeatId();
    }
}
