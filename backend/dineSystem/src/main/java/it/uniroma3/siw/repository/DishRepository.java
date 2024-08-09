package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish,Integer> {
    //List<Dish> findAllById();
}
