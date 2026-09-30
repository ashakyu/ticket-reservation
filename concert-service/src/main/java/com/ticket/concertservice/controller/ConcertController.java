package com.ticket.concertservice.controller;

import com.ticket.concertservice.dto.request.CreateConcertRequest;
import com.ticket.concertservice.dto.response.ConcertResponse;
import com.ticket.concertservice.dto.response.SeatResponse;
import com.ticket.concertservice.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/concerts")
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertService concertService;

    @PostMapping
    public ResponseEntity<ConcertResponse> createConcert(@RequestBody CreateConcertRequest request) {
        return ResponseEntity.ok(concertService.createConcert(request));
    }

    @GetMapping
    public ResponseEntity<List<ConcertResponse>> getAvailableConcerts() {
        return ResponseEntity.ok(concertService.getAvailableConcerts());
    }

    @GetMapping("/{concertId}")
    public ResponseEntity<ConcertResponse> getConcert(@PathVariable Long concertId) {
        return ResponseEntity.ok(concertService.getConcert(concertId));
    }

    @GetMapping("/{concertId}/seats")
    public ResponseEntity<List<SeatResponse>> getAvailableSeats(@PathVariable Long concertId) {
        return ResponseEntity.ok(concertService.getAvailableSeats(concertId));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("concert-service is running");
    }
}