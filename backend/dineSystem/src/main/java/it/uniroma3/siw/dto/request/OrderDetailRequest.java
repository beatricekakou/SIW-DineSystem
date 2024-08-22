package it.uniroma3.siw.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailRequest {
    private Integer dishId;
    private Integer quantity;
}