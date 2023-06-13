package com.exclaimation.librarysystem.service;

import com.exclaimation.librarysystem.entity.RentEntity;
import com.exclaimation.librarysystem.repository.RentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyRentService {

    public final RentRepository rentRepository;

    public MyRentService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    public List<RentEntity> myRentList(String studentId) {

        List<RentEntity> rentList = rentRepository.findByStudentId(studentId);

        List<RentEntity> nowRentList = new ArrayList<>();
        for(int i = 0; i < rentList.size(); i++){
            if(!rentList.get(i).is_return())
                nowRentList.add(rentList.get(i));
        }
        return nowRentList;
    }
}
