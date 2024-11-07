package com.coffeemachine.demo.repository;

import com.coffeemachine.demo.entity.CoffeeReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoffeeRecipeRepository extends JpaRepository<CoffeeReceipt,Integer> {

    Optional<CoffeeReceipt> findByName(String name);

    @Query("SELECT c.name from CoffeeReceipt c where c.sold > 0 order by c.sold DESC")
    List<String> getPopularCoffee();

}
