package com.exclaimation.librarysystem.repository;

import com.exclaimation.librarysystem.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findById(String id);
}
