package com.ticket.concertservice.repository;

import com.ticket.concertservice.domain.entity.Seat;
import com.ticket.concertservice.domain.entity.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByConcertIdAndStatus(Long concertId, SeatStatus status);
}