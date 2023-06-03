package com.exclaimation.librarysystem.repository;

import com.exclaimation.librarysystem.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(String id);
    Optional<Member> findByPw(String pw);
    List<Member> findAll();
}
