package it.uniroma3.siw.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetOrdersResponse {
    private LocalDateTime date;
    private List<OrderItem> dishes;
    private Double amount;
}
