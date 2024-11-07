package com.coffeemachine.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "coffee")
public class CoffeeReceipt {
        @Id
        @Column(name = "coffee_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "name")
        private String name;

        @Column(name = "coffee_beans")
        private int coffee_beans;

        @Column(name = "water")
        private int water;

        @Column(name = "milk")
        private int milk;

        @Column(name = "sold_quantity")
        private int sold = 0;

        @Column(name = "sugar")
        private boolean sugar;

        @ElementCollection(targetClass = String.class,fetch = FetchType.LAZY)
        @CollectionTable(name = "steps", joinColumns = @JoinColumn(name = "coffee_id"))
        @Column(name = "instruction")
        private List<String> instructions;
}
