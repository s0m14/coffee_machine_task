package com.coffeemachine.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResourcesDto {
    private int water;
    private int milk;
    private int coffee_beans;
}
