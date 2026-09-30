package com.ticket.concertservice.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CreateConcertRequest {
    private String title;
    private String artist;
    private String venue;
    private LocalDateTime concertDate;
    private int totalSeats;
    private int price;
    private LocalDateTime openDate;
}