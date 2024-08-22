package it.uniroma3.siw.service;

import it.uniroma3.siw.dto.request.CreateOrderRequest;
import it.uniroma3.siw.dto.request.OrderDetailRequest;
import it.uniroma3.siw.dto.response.GetOrdersResponse;
import it.uniroma3.siw.exception.UserNotFoundException;
import it.uniroma3.siw.mapper.OrderMapper;
import it.uniroma3.siw.model.*;
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
    private CredentialService credentialService;
    EmailService emailService;
    UserService userService;

    public Order createOrder(CreateOrderRequest orderDTO) throws MessagingException {
        User user = userService.getUserByUserName(orderDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("user not found"));
        Order orderEntity = new Order();
        orderEntity.setUser(user);
        orderEntity.setDate(LocalDateTime.now());
        this.orderRepository.save(orderEntity);

        double amount = 0;
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (OrderDetailRequest detailDTO : orderDTO.getOrderDetails()) {
            OrderDetail detailEntity = createOrderDetail(detailDTO, orderEntity);
       /*     detailEntity.setOrder(orderEntity);
            Dish dish = this.dishRepository.findById(detailDTO.getDishId()).get();
            detailEntity.setDish(dish);
            detailEntity.setQuantity(detailDTO.getQuantity());
            this.orderDetailRepository.save(detailEntity);*/
            orderDetailList.add(detailEntity);
            amount += detailEntity.getDish().getPrice() * detailEntity.getQuantity();
        }
        orderEntity.setAmount(amount);
        orderEntity.setOrderDetails(orderDetailList);

        Order orderSaved = this.orderRepository.save(orderEntity);
        if (Objects.nonNull(orderSaved))
            emailService.sendOrderEmail(orderSaved);


        return orderEntity;
    }

    public OrderDetail createOrderDetail(OrderDetailRequest orderDetailDTO, Order order) {
        OrderDetail detailEntity = new OrderDetail();

        Dish dish = this.dishRepository.findById(orderDetailDTO.getDishId()).get();
        detailEntity.setDish(dish);
        detailEntity.setQuantity(orderDetailDTO.getQuantity());
        detailEntity.setOrder(order);

        return this.orderDetailRepository.save(detailEntity);
    }

    public List<GetOrdersResponse> findAllByUser(String username) {
        Credential credential = this.credentialService.findByUserName(username);
        User user = this.userService.findByCredential(credential);
        List<Order> orders = this.orderRepository.findAllByUserId(user.getId());

        return OrderMapper.fromOrdersToGetOrdersResponseList(orders);
    }
}
