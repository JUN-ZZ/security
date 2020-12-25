package com.jun.reservation.dao;

import com.jun.reservation.entity.ReservationOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationOrderRepository extends JpaRepository<ReservationOrder, Long> {

}
