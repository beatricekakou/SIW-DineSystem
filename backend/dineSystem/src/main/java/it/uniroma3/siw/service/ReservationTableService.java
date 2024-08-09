package it.uniroma3.siw.service;

import it.uniroma3.siw.model.ReservationTable;
import it.uniroma3.siw.model.SlotEntity;
import it.uniroma3.siw.repository.ReservationTableRepository;
import it.uniroma3.siw.utils.Slot;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class ReservationTableService {
    private ReservationTableRepository reservationTableRepository;

    public ReservationTable getAvailableTable(int capacity, LocalDate date, SlotEntity seating) {
        List<ReservationTable> reservationTableList = this.reservationTableRepository.findBookableTables(capacity,date,seating.getId());
        ReservationTable reservationTable = (!Objects.isNull(reservationTableList) && reservationTableList.size() != 0) ? reservationTableList.get(0) : null;
        return reservationTable;
    }

    public List<Slot> getAvailableTables(int capacity, LocalDate date) {
        List<ReservationTable> reservationTableList = this.reservationTableRepository.findBookableTablesNoSlot(capacity,date);
        return null;
    }

}
