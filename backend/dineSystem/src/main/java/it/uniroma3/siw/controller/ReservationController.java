package it.uniroma3.siw.controller;

import it.uniroma3.siw.dto.ReservationDTO;
import it.uniroma3.siw.service.ReservationService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {
    private ReservationService reservationService;

    @PostMapping("/new")
    @CrossOrigin
    public ResponseEntity<?> createReservation(@RequestBody ReservationDTO reservation) throws MessagingException {
        boolean isReservationSaved = this.reservationService.createReservation(reservation);
        return isReservationSaved ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @GetMapping("/available-slots")
    @CrossOrigin
    public ResponseEntity<Set<String>> getAvailableSlots(@RequestParam LocalDate reservationDate, @RequestParam int numberOfPeople) {
        Set<String> availableSlots = this.reservationService.findAvailableSlots(reservationDate,numberOfPeople);
        return Objects.isNull(availableSlots) ? new ResponseEntity<>(new HashSet<>(),HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(availableSlots,HttpStatus.OK);
    }
}
