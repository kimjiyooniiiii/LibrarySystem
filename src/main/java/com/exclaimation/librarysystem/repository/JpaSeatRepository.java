package com.exclaimation.librarysystem.repository;

import com.exclaimation.librarysystem.entity.SeatEntity;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaSeatRepository implements SeatRepository{

    private final EntityManager em;

    public JpaSeatRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(SeatEntity seatEntity) {
        em.persist(seatEntity);
    }

    @Override
    public List<SeatEntity> findAll() {
        List<SeatEntity> result = em.createQuery("select s from seat s", SeatEntity.class)
                .getResultList();
        return result;
    }

    @Override
    public Optional<SeatEntity> findById(Long seatId) {
        SeatEntity seatEntity = em.find(SeatEntity.class, seatId);
        return Optional.ofNullable(seatEntity);
    }
}
