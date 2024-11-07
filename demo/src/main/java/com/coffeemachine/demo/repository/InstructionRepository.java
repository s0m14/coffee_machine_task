package com.coffeemachine.demo.repository;

import com.coffeemachine.demo.entity.CoffeeReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructionRepository extends JpaRepository<CoffeeReceipt,Integer> {
}
