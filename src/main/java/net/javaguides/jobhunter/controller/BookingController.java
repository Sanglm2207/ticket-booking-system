package net.javaguides.jobhunter.controller;

import net.javaguides.jobhunter.domain.request.ReqBookingDTO;
import net.javaguides.jobhunter.service.BookingService;
import net.javaguides.jobhunter.util.annotation.ApiMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/bookings")
    public ResponseEntity<String> bookTicket(@RequestBody ReqBookingDTO reqBookingDTO) {
        String response = bookingService.bookTicket(reqBookingDTO.getUserId(), reqBookingDTO.getTicketId());
        return ResponseEntity.ok(response);
    }
}
