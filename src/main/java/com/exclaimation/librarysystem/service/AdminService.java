package com.exclaimation.librarysystem.service;

import com.exclaimation.librarysystem.entity.Rent;
import com.exclaimation.librarysystem.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private final RentRepository rentRepository;

    public AdminService(RentRepository repository) {
        this.rentRepository = repository;
    }

    // 회원들 대출 목록 보기
    public List<Rent> showRentList() {
        List<Rent> rentList = rentRepository.findAll();

        return rentList;
    }
}
