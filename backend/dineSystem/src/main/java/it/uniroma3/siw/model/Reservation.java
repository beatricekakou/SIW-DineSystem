package it.uniroma3.siw.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    @ToString.Exclude
    private User user;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private ReservationTable reservationTable;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "slot_id", referencedColumnName = "id")
    private SlotEntity slot;

    @Column(name = "number_people")
    private Integer numberPeople;
}
