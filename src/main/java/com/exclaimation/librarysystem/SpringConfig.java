package com.exclaimation.librarysystem;

import com.exclaimation.librarysystem.repository.BookRepository;
import com.exclaimation.librarysystem.repository.RentRepository;
import com.exclaimation.librarysystem.repository.ReserveRepository;
import com.exclaimation.librarysystem.repository.SeatRepository;
import com.exclaimation.librarysystem.service.RentService;
import com.exclaimation.librarysystem.service.ReserveService;
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
    private final SeatRepository seatRepository;
    private final ReserveRepository reserveRepository;
    private final RentRepository rentRepository;
    private final BookRepository bookRepository;
    @Autowired
    public SpringConfig(EntityManager em,
                        SeatRepository seatRepository,
                        ReserveRepository reserveRepository,
                        RentRepository rentRepository, BookRepository bookRepository){
        this.em = em;
        this.seatRepository = seatRepository;
        this.reserveRepository = reserveRepository;
        this.rentRepository = rentRepository;
        this.bookRepository = bookRepository;
    }
    @Bean
    public RentService rentService(){ return  new RentService(rentRepository, bookRepository); }

    @Bean
    public ReserveService reserveService(){
        return new ReserveService(reserveRepository);
    }

    @Bean
    public SeatService seatService(){
        return new SeatService(seatRepository);
    }

}
