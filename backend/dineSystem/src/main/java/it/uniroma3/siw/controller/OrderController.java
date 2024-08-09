package it.uniroma3.siw.controller;

import it.uniroma3.siw.dto.OrderDTO;
import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.service.OrderService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("order")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping("/new")
    @CrossOrigin
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO ordetDTO) throws MessagingException {
        Order order = orderService.createOrder(ordetDTO);
        return Objects.isNull(order) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(HttpStatus.OK);

    }
}
