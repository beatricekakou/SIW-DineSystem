package it.uniroma3.siw.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationRequest {

    private String userId;
    private LocalDate date;
    private String slotTime;
    private Integer numberOfPeople;
}
