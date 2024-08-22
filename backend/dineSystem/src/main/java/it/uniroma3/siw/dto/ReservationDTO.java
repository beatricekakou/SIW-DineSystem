package it.uniroma3.siw.dto;

import it.uniroma3.siw.model.SlotEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReservationDTO {
    private LocalDate date;
    private SlotEntity slot;
    private Integer numberPeople;
}
