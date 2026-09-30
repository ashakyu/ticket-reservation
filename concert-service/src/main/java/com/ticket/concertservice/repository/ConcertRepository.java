package com.ticket.concertservice.repository;

import com.ticket.concertservice.domain.entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.time.LocalDateTime;

public interface ConcertRepository extends JpaRepository<Concert, Long> {
    List<Concert> findByOpenDateBeforeAndAvailableSeatsGreaterThan(
            LocalDateTime now, int seats
    );
}