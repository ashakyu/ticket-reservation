package com.ticket.concertservice.dto.response;

import com.ticket.concertservice.domain.entity.Seat;
import com.ticket.concertservice.domain.entity.SeatStatus;
import lombok.Getter;

@Getter
public class SeatResponse {
    private Long id;
    private String seatNumber;
    private SeatStatus status;

    public SeatResponse(Seat seat) {
        this.id = seat.getId();
        this.seatNumber = seat.getSeatNumber();
        this.status = seat.getStatus();
    }
}