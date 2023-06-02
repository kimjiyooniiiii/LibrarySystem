package com.exclaimation.librarysystem;

import com.exclaimation.librarysystem.repository.JpaSeatRepository;
import com.exclaimation.librarysystem.repository.SeatRepository;
import com.exclaimation.librarysystem.service.SeatService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }

    @Bean
    public SeatService seatService(){
        return new SeatService(seatRepository());
    }

    private SeatRepository seatRepository() {
        return new JpaSeatRepository(em);
    }
}
