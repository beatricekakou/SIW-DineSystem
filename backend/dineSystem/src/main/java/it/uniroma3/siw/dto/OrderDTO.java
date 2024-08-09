package it.uniroma3.siw.dto;

import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String userId;
    private List<OrderDetailDTO> orderDetails;
}
