package com.exclaimation.librarysystem.service;

import com.exclaimation.librarysystem.entity.RentEntity;
import com.exclaimation.librarysystem.entity.Require;
import com.exclaimation.librarysystem.entity.ReserveEntity;
import com.exclaimation.librarysystem.repository.RentRepository;
import com.exclaimation.librarysystem.repository.RequireRepository;
import com.exclaimation.librarysystem.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private final RentRepository rentRepository;

    @Autowired
    private final RequireRepository requireRepository;

    @Autowired
    private final ReserveRepository reserveRepository;

    public AdminService(RentRepository repository, RequireRepository requireRepository, ReserveRepository reserveRepository) {
        this.rentRepository = repository;
        this.requireRepository = requireRepository;
        this.reserveRepository = reserveRepository;
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
}
