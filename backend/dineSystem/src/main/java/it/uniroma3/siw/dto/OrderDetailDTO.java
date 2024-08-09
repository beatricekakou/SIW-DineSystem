package it.uniroma3.siw.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private Integer dishId;
    private Integer quantity;
}