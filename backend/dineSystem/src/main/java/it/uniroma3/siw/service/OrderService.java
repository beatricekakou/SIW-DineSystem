package it.uniroma3.siw.service;

import it.uniroma3.siw.dto.OrderDTO;
import it.uniroma3.siw.dto.OrderDetailDTO;
import it.uniroma3.siw.exception.UserNotFoundException;
import it.uniroma3.siw.model.Dish;
import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.model.OrderDetail;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.DishRepository;
import it.uniroma3.siw.repository.OrderDetailRepository;
import it.uniroma3.siw.repository.OrderRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    private DishRepository dishRepository;
    EmailService emailService;
    UserService userService;

    public Order createOrder(OrderDTO orderDTO) throws MessagingException {
        User user = userService.getUserByUserName(orderDTO.getUserId())
                .orElseThrow(()-> new UserNotFoundException("user not found"));
        Order orderEntity = new Order();
        orderEntity.setUser(user);
        orderEntity.setDate(LocalDateTime.now());
        this.orderRepository.save(orderEntity);

        double amount = 0;
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (OrderDetailDTO detailDTO : orderDTO.getOrderDetails()) {

            //orderDetailList.add(createOrderDetail(detailDTO, orderEntity));
            OrderDetail detailEntity = new OrderDetail();
            detailEntity.setOrder(orderEntity);
            Dish dish = this.dishRepository.findById(detailDTO.getDishId()).get();
            detailEntity.setDish(dish);
            detailEntity.setQuantity(detailDTO.getQuantity());
            this.orderDetailRepository.save(detailEntity);
            orderDetailList.add(detailEntity);
            amount += dish.getPrice() * detailEntity.getQuantity();
        }
        orderEntity.setAmount(amount);
        orderEntity.setOrderDetails(orderDetailList);

        Order orderSaved = this.orderRepository.save(orderEntity);
        if(Objects.nonNull(orderSaved))
            emailService.sendOrderEmail(orderSaved);


        return orderEntity;
    }

    public OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO, Order order) {
        OrderDetail detailEntity = new OrderDetail();
        detailEntity.setOrder(order);
        Dish dish = this.dishRepository.findById(orderDetailDTO.getDishId()).get();
        detailEntity.setDish(dish);
        detailEntity.setQuantity(orderDetailDTO.getQuantity());
        this.orderDetailRepository.save(detailEntity);

        return detailEntity;
    }
}
