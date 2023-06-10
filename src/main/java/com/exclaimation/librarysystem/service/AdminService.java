package com.exclaimation.librarysystem.service;

import com.exclaimation.librarysystem.entity.RentEntity;
import com.exclaimation.librarysystem.entity.Require;
import com.exclaimation.librarysystem.repository.RentRepository;
import com.exclaimation.librarysystem.repository.RequireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private final RentRepository rentRepository;

    @Autowired
    private final RequireRepository requireRepository;

    public AdminService(RentRepository repository, RequireRepository requireRepository) {
        this.rentRepository = repository;
        this.requireRepository = requireRepository;
    }

    // 회원들 대출 목록 보기
    public List<RentEntity> showRentList() {
        List<RentEntity> rentList = rentRepository.findAll();

        return rentList;
    }

    // 희망 도서 목록 보기
    public List<Require> showRequireList() {
        List<Require> requireList = requireRepository.findAll();

        return requireList;
    }
}
