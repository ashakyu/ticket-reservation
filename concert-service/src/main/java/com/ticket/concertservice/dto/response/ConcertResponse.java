package com.ticket.concertservice.dto.response;

import com.ticket.concertservice.domain.entity.Concert;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ConcertResponse {
    private Long id;
    private String title;
    private String artist;
    private String venue;
    private LocalDateTime concertDate;
    private int availableSeats;
    private int price;
    private LocalDateTime openDate;

    public ConcertResponse(Concert concert) {
        this.id = concert.getId();
        this.title = concert.getTitle();
        this.artist = concert.getArtist();
        this.venue = concert.getVenue();
        this.concertDate = concert.getConcertDate();
        this.availableSeats = concert.getAvailableSeats();
        this.price = concert.getPrice();
        this.openDate = concert.getOpenDate();
    }
}