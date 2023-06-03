package com.exclaimation.librarysystem.repository;

import com.exclaimation.librarysystem.entity.Rent;
import com.exclaimation.librarysystem.entity.Require;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequireRepository extends JpaRepository<Require, Long> {

}
