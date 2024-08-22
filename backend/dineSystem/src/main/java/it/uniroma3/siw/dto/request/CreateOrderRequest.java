package it.uniroma3.siw.dto.request;

import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    private String userId;
    private List<OrderDetailRequest> orderDetails;
}
