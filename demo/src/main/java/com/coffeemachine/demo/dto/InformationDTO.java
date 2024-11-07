package com.coffeemachine.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InformationDTO {
    private String name;
    private int coffee_beans;
    private int water;
    private int milk;
    private boolean sugar;
    private List<String> instruction;
    private int sold_quantity;
}
