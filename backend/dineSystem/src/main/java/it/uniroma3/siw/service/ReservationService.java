package it.uniroma3.siw.service;

import it.uniroma3.siw.dto.ReservationDTO;
import it.uniroma3.siw.dto.request.CreateReservationRequest;
import it.uniroma3.siw.exception.UserNotFoundException;
import it.uniroma3.siw.mapper.ReservationMapper;
import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.ReservationRepository;
import it.uniroma3.siw.utils.Slot;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService {

    private ReservationRepository reservationRepository;
    private ReservationTableService reservationTableService;
    private UserService userService;
    private CredentialService credentialService;
    private EmailService emailService;

    /**
     * Creates a reservation based on the provided ReservationDTO.
     *
     * @param reservation The reservation data transfer object.
     * @return true if The reservation was successfully created, false otherwise.
     */
    public boolean createReservation(CreateReservationRequest reservation) throws MessagingException {
        SlotEntity seating = Slot.getSlotFromTimeString(reservation.getSlotTime()).toEntity();
        ReservationTable bookableTable = this.reservationTableService.getAvailableTable(reservation.getNumberOfPeople(), reservation.getDate(), seating);
        if (Objects.isNull(bookableTable))
            return false;

        Reservation reservationEntity = new Reservation();
        User user = this.userService.getUserByUserName(reservation.getUserId()).orElseThrow(() -> new UserNotFoundException("user not found"));
        reservationEntity.setUser(user);
        reservationEntity.setReservationTable(bookableTable);
        reservationEntity.setDate(reservation.getDate());
        reservationEntity.setNumberPeople(reservation.getNumberOfPeople());
        reservationEntity.setSlot(seating);
        Reservation reservationSaved = this.reservationRepository.save(reservationEntity);

            if(Objects.nonNull(reservationSaved))
                emailService.sendReservationEmail(reservationSaved);

        return true;
    }

    /**
     * Finds available slots for a given date and number of people.
     *
     * @param date           The reservation date.
     * @param numberOfPeople The number of people for the reservation.
     * @return A list of available slot descriptions.
     */
    public Set<String> findAvailableSlots(LocalDate date, int numberOfPeople) {
        List<Reservation> reservations = this.reservationRepository.findByDateAndNumberPeople(date, numberOfPeople);
        List<String> bookedSlots = reservations.stream().map(r -> r.getSlot().getTime().toString()).collect(Collectors.toList());
        List<String> totalSlots = this.getTotalSlotsForCapacity(numberOfPeople);

        if (reservations.size() == 0) {
            return new HashSet<>(Slot.getTimes());
        }

        totalSlots.removeAll(bookedSlots);

        return  new HashSet<>(totalSlots);
    }

    /**
     * Gets the total slots available for a specific capacity.
     *
     * @param capacity The number of people for the reservation.
     * @return A list of total slot descriptions.
     */
    public List<String> getTotalSlotsForCapacity(int capacity) {
        int numberOfTables = getNumberOfTablesForCapacity(capacity);

        List<String> slotTimes = Slot.getTimes();
        List<String> totalSlots = new ArrayList<>();

        for (int i = 0; i < numberOfTables; i++) {
            totalSlots.addAll(slotTimes);
        }

        return totalSlots;
    }

    public List<ReservationDTO> findAllByUsername(String username) {
        Credential credential = this.credentialService.findByUserName(username);
        User user = this.userService.findByCredential(credential);
        List<Reservation> reservations = this.reservationRepository.findAllByUserId(user.getId());
        return ReservationMapper.fromReservationEntitiesToDTOList(reservations);
    }
    /**
     * Gets the number of tables available for a specific capacity.
     *
     * @param capacity The number of people for the reservation.
     * @return The number of tables.
     */
    private int getNumberOfTablesForCapacity(int capacity) {
        switch (capacity) {
            case 2:
                return 10;
            case 4:
                return 10;
            case 6:
                return 5;
            case 8:
                return 3;
            case 12:
                return 2;
            default:
                throw new IllegalArgumentException("the capacity number isn't valid: " + capacity);
        }
    }
}

