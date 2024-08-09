package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    List<Reservation> findByDateAndNumberPeople(LocalDate date,int numberOfPeople);
}
