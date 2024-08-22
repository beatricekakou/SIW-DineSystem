package it.uniroma3.siw.mapper;

import it.uniroma3.siw.dto.response.OrderItem;
import it.uniroma3.siw.dto.response.GetOrdersResponse;

import it.uniroma3.siw.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static List<GetOrdersResponse> fromOrdersToGetOrdersResponseList(List<Order> orders) {
        List<GetOrdersResponse> getOrdersResponses = new ArrayList<>();

        orders.forEach(o -> {
            List<OrderItem> dishes = new ArrayList<>();
            o.getOrderDetails().forEach(od -> dishes.add(new OrderItem(
                    od.getDish().getName(),
                    od.getQuantity(),
                    od.getDish().getPrice()
                    )));
            getOrdersResponses.add(
                    GetOrdersResponse.builder().amount(o.getAmount()).date(o.getDate()).dishes(dishes).build()
            );
        });
        return getOrdersResponses;
    }
}
