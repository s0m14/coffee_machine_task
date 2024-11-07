package com.coffeemachine.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientResponseDTO {
    private String coffee_type;
    private String message;
}
