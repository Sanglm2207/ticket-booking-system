package net.javaguides.jobhunter.service.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.jobhunter.domain.Booking;
import net.javaguides.jobhunter.domain.Ticket;
import net.javaguides.jobhunter.domain.User;
import net.javaguides.jobhunter.repository.BookingRepository;
import net.javaguides.jobhunter.repository.TicketRepository;
import net.javaguides.jobhunter.repository.UserRepository;
import net.javaguides.jobhunter.util.constant.BookingStatusEnum;
import net.javaguides.jobhunter.util.constant.TicketStatusEnum;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {
    private final BookingRepository bookingRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;


    @KafkaListener(topics = "booking_created", groupId = "booking-group")
    public void processBooking(String message) {
        try {
            log.info("Received booking request: {}", message);
//            Long ticketId = Long.parseLong(message);
            JsonNode jsonNode = objectMapper.readTree(message);
            Long ticketId = jsonNode.get("ticketId").asLong();
            Long userId = jsonNode.get("userId").asLong();

            Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
            User user = userRepository.findById(userId).orElse(null);

            if (ticket != null && ticket.getStatus() == TicketStatusEnum.AVAILABLE) {
                ticket.setStatus(TicketStatusEnum.BOOKED);
                ticketRepository.save(ticket);
                log.info("Ticket {} booked successfully!", ticketId);

                // Create and save booking information
                Booking booking = new Booking();
                booking.setTicket(ticket);
                booking.setUser(user);
                booking.setStatus(BookingStatusEnum.PENDING);
                bookingRepository.save(booking);
                log.info("Booking {} booked successfully!", ticketId);
            } else {
                log.error("Ticket {} is unavailable", ticketId);
            }
        } catch (Exception exception) {
            log.error("Error processing booking", exception);
        }
    }
}
