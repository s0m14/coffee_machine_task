package com.coffeemachine.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ingredients")
public class Ingredients {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "coffee_beans")
    private int coffeeBeans;

    @Column(name = "water")
    private int water;

    @Column(name = "milk")
    private int milk;
}
