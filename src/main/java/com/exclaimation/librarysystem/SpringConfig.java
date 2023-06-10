package com.exclaimation.librarysystem;

import com.exclaimation.librarysystem.repository.MemberRepository;
import com.exclaimation.librarysystem.repository.RentRepository;
import com.exclaimation.librarysystem.repository.ReserveRepository;
import com.exclaimation.librarysystem.repository.SeatRepository;
import com.exclaimation.librarysystem.service.MemberService;
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
    @Autowired
    public SpringConfig(EntityManager em,
                        SeatRepository seatRepository,
                        MemberRepository memberRepository,
                        ReserveRepository reserveRepository,
                        RentRepository rentRepository){
        this.em = em;
        this.seatRepository = seatRepository;
        this.memberRepository = memberRepository;
        this.reserveRepository = reserveRepository;
        this.rentRepository = rentRepository;
    }
    @Bean
    public RentService rentService(){ return  new RentService(rentRepository); }

    @Bean
    public ReserveService reserveService(){
        return new ReserveService(reserveRepository);
    }

    @Bean
    public SeatService seatService(){
        return new SeatService(seatRepository);
    }

    private final MemberRepository memberRepository;

    @Bean
    public MemberService memberService() {
        //return new MemberService(memberRepository()); //memberRepository를 연결함
        return new MemberService(memberRepository); //스프링 데이터 JPA를 위해 사용함
    }
}
