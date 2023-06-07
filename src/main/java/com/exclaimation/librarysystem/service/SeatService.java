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


        boolean enable = seatForm.isEnable();
        if(enable) { // 좌석 예약
            List<Seat.Simple> list = findSeats();
            boolean isMySeat = false;
            for(int i = 0; i < 48; i++)
            {
                if( list.get(i).getStudent_id().equals(seatForm.getStudent_id()) )
                    isMySeat = true;
            }

            if(isMySeat && !seatForm.getStudent_id().equals("0"))
            {
                System.out.println("한 사람이 두 자리 이상을 예약할 수 없습니다.");
                return;
            }

            seatEntity.setStudent_id(seatForm.getStudent_id());
        }
        else{ // 좌석 반납
            if(!seatEntity.getStudent_id().equals(seatForm.getStudent_id()) && !seatForm.getStudent_id().equals("0")){
                System.out.println("다른 사용자의 사용중인 좌석은 반납할 수 없습니다.");
                return;
            }

            seatEntity.setStudent_id("0");
        }
        seatEntity.setEnable(seatForm.isEnable()^true);
        seatRepository.save(seatEntity);
    }
}
