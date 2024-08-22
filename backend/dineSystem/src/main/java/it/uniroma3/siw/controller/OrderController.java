package it.uniroma3.siw.controller;

import it.uniroma3.siw.dto.request.CreateOrderRequest;
import it.uniroma3.siw.dto.response.GetOrdersResponse;
import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.service.OrderService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("order")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping("/new")
    @CrossOrigin
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest orderRequest) throws MessagingException {
        Order order = orderService.createOrder(orderRequest);
        return Objects.isNull(order) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/all-by-username")
    @CrossOrigin
    public ResponseEntity<?> getAllOrdersByUsername(@RequestParam("username")String username) {
        List<GetOrdersResponse> orders = this.orderService.findAllByUser(username);
        return (Objects.isNull(orders) || orders.isEmpty())? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(orders,HttpStatus.OK);
    }
}
