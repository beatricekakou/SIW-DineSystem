package it.uniroma3.siw.controller;


import it.uniroma3.siw.model.Dish;
import it.uniroma3.siw.service.DishService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dish")
@AllArgsConstructor
public class DishController {

    private DishService dishService;

    @GetMapping("/all")
    @CrossOrigin
    public ResponseEntity<List<Dish>> getAllDishes() {
        return new ResponseEntity<>(this.dishService.getAllDishes(), HttpStatus.OK);

    }
}
