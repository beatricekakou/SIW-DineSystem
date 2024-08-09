package it.uniroma3.siw.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservation_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationTable { // 'Table' è una parola riservata in Java, quindi la rinominiamo in 'TableEntity'
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;

    private Integer capacity;
}

