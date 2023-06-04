package com.exclaimation.librarysystem.service;

import com.exclaimation.librarysystem.domain.Member;
import com.exclaimation.librarysystem.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {  //생성자 함수를 통한 DI 자동 주입
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public String join(Member member) {
        System.out.println();
        System.out.println("<<<< MemberService.join 회원가입 서비스 시작 >>>> ");
        System.out.println("member.id = " + member.getId());
        System.out.println("member.pw = " + member.getPw());
        validateDuplicateMember(member);  //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findById(member.getId());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
            memberRepository.findById(member.getId())
                    .ifPresent(m -> {
                        throw new IllegalStateException("이미 존재하는 회원입니다.");
                    });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(String memberId) {
        return memberRepository.findById(memberId);
    }
}
