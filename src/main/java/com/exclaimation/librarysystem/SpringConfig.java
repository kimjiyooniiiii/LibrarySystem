package com.exclaimation.librarysystem;

import com.exclaimation.librarysystem.repository.JpaSeatRepository;
import com.exclaimation.librarysystem.repository.MemberRepository;
import com.exclaimation.librarysystem.repository.SeatRepository;
import com.exclaimation.librarysystem.service.MemberService;
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
    public SpringConfig(EntityManager em, MemberRepository memberRepository){
        this.em = em;
        this.memberRepository = memberRepository;
    }

    @Bean
    public SeatService seatService(){
        return new SeatService(seatRepository());
    }

    private SeatRepository seatRepository() {
        return new JpaSeatRepository(em);
    }

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        //return new MemberService(memberRepository()); //memberRepository를 연결함
        return new MemberService(memberRepository); //스프링 데이터 JPA를 위해 사용함
    }
}
