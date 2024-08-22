package it.uniroma3.siw.mapper;

import it.uniroma3.siw.dto.ReservationDTO;
import it.uniroma3.siw.model.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationMapper {

    public static List<ReservationDTO> fromReservationEntitiesToDTOList(List<Reservation> reservations) {
        List<ReservationDTO> reservationDTOList = new ArrayList<>();

        reservations.forEach(r -> {

            reservationDTOList.add(
                    ReservationDTO.builder().date(r.getDate()).numberPeople(r.getNumberPeople()).slot(r.getSlot()).build()
            );
        });
        return reservationDTOList;
    }
}
