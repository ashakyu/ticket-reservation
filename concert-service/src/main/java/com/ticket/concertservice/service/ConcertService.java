package com.ticket.concertservice.service;

import com.ticket.concertservice.domain.entity.Concert;
import com.ticket.concertservice.domain.entity.Seat;
import com.ticket.concertservice.domain.entity.SeatStatus;
import com.ticket.concertservice.dto.request.CreateConcertRequest;
import com.ticket.concertservice.dto.response.ConcertResponse;
import com.ticket.concertservice.dto.response.SeatResponse;
import com.ticket.concertservice.repository.ConcertRepository;
import com.ticket.concertservice.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConcertService {

    private final ConcertRepository concertRepository;
    private final SeatRepository seatRepository;

    @Transactional
    public ConcertResponse createConcert(CreateConcertRequest request) {
        Concert concert = Concert.builder()
                .title(request.getTitle())
                .artist(request.getArtist())
                .venue(request.getVenue())
                .concertDate(request.getConcertDate())
                .totalSeats(request.getTotalSeats())
                .availableSeats(request.getTotalSeats())
                .price(request.getPrice())
                .openDate(request.getOpenDate())
                .build();

        concertRepository.save(concert);
        createSeats(concert);

        return new ConcertResponse(concert);
    }

    @Transactional(readOnly = true)
    public List<ConcertResponse> getAvailableConcerts() {
        return concertRepository
                .findByOpenDateBeforeAndAvailableSeatsGreaterThan(LocalDateTime.now(), 0)
                .stream()
                .map(ConcertResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ConcertResponse getConcert(Long concertId) {
        Concert concert = concertRepository.findById(concertId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공연입니다."));
        return new ConcertResponse(concert);
    }

    @Transactional(readOnly = true)
    public List<SeatResponse> getAvailableSeats(Long concertId) {
        return seatRepository.findByConcertIdAndStatus(concertId, SeatStatus.AVAILABLE)
                .stream()
                .map(SeatResponse::new)
                .collect(Collectors.toList());
    }

    private void createSeats(Concert concert) {
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= concert.getTotalSeats(); i++) {
            seats.add(Seat.builder()
                    .concert(concert)
                    .seatNumber(String.format("A%03d", i))
                    .status(SeatStatus.AVAILABLE)
                    .build());
        }
        seatRepository.saveAll(seats);
    }
}