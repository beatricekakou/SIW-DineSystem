package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.ReservationTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationTableRepository extends JpaRepository<ReservationTable, Integer> {
    @Query("SELECT t FROM ReservationTable t WHERE t.capacity = :capacity " +
            "AND NOT EXISTS (SELECT r FROM Reservation r WHERE r.reservationTable = t " +
            "AND r.date = :date AND r.slot.id = :seatingId)")
    List<ReservationTable> findBookableTables(@Param("capacity") int capacity,
                                              @Param("date") LocalDate date,
                                              @Param("seatingId") int seatingId);

    @Query("SELECT t FROM ReservationTable t WHERE t.capacity >= :capacity " +
            "AND NOT EXISTS (SELECT r FROM Reservation r WHERE r.reservationTable = t " +
            "AND r.date = :date )")
    List<ReservationTable> findBookableTablesNoSlot(@Param("capacity") int capacity,
                                              @Param("date") LocalDate date);
}
