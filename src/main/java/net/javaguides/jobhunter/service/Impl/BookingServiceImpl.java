package net.javaguides.jobhunter.service.Impl;

import net.javaguides.jobhunter.domain.Ticket;
import net.javaguides.jobhunter.repository.BookingRepository;
import net.javaguides.jobhunter.repository.TicketRepository;
import net.javaguides.jobhunter.service.BookingService;
import net.javaguides.jobhunter.service.event.KafkaProducer;
import net.javaguides.jobhunter.util.constant.TicketStatusEnum;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final TicketRepository ticketRepository;
    private final KafkaProducer kafkaProducer;
    private final RedisTemplate<String, String> redisTemplate;

    public BookingServiceImpl(BookingRepository bookingRepository, TicketRepository ticketRepository, KafkaProducer kafkaProducer, RedisTemplate<String, String> redisTemplate) {
        this.bookingRepository = bookingRepository;
        this.ticketRepository = ticketRepository;
        this.kafkaProducer = kafkaProducer;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String bookTicket(Long userId, Long ticketId) {
        String cacheKey = "ticket:" + ticketId;

        if (Boolean.FALSE.equals(redisTemplate.opsForValue().setIfAbsent(cacheKey, "booked", 10, TimeUnit.MINUTES))) {
            return "Ticket is already booked by someone else";
        }

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
        if (ticket.getStatus() != TicketStatusEnum.AVAILABLE) {
            return "Ticket is not available";
        }
        // Create a JSON message with both ticketId and userId
        String message = String.format("{\"ticketId\": %d, \"userId\": %d}", ticketId, userId);
        kafkaProducer.sendMessage("booking_created", message);

        return "Booking request sent!";
    }
}
