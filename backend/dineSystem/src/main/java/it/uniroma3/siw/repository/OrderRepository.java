package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findAllByUserId(Long userId);
}
