package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Dish;
import it.uniroma3.siw.repository.DishRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DishService {
    private DishRepository dishRepository;

    public List<Dish> getAllDishes() {
        return this.dishRepository.findAll();
    }
}
