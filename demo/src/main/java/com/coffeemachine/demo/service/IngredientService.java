package com.coffeemachine.demo.service;

import com.coffeemachine.demo.entity.Ingredients;
import com.coffeemachine.demo.exception.RequestException;
import com.coffeemachine.demo.repository.IngredientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Cacheable("ingredients")
    public Ingredients ingredients(){
        return ingredientsRepository.findById(1000L).orElseThrow(()->new RuntimeException("Coffee not found"));
    }

    public int getWaterAmount() {
        return ingredients().getWater();
    }

    public int getMilkAmount(){
        return ingredients().getMilk();
    }

    public int getCoffeeBeansAmount(){
        return ingredients().getCoffeeBeans();
    }

    public Ingredients fillCoffeeBeans(int amount) {
        if(amount + getCoffeeBeansAmount() > 500){
            throw new RequestException("Maximum capacity of coffee beans is reached");
        }else{
            ingredients().setCoffeeBeans(amount + getCoffeeBeansAmount());
            return ingredientsRepository.save(ingredients());
        }
    }

    public Ingredients fillWater(int amount) {
        if(amount + getWaterAmount() > 1000){
            throw new RequestException("Maximum capacity of water is reached");
        }else{
            ingredients().setWater(amount + getWaterAmount());
            return ingredientsRepository.save(ingredients());
        }
    }

    public Ingredients fillMilk(int amount)  {
        if(amount + getMilkAmount() > 500){
            throw new RequestException("Maximum capacity of milk reached");
        }else{
            ingredients().setMilk(amount + getMilkAmount());
            return ingredientsRepository.save(ingredients());
        }
    }

    public boolean isOk(){
        return getCoffeeBeansAmount() >= 0 && getMilkAmount() >= 0 && getWaterAmount() >= 0;
    }

    public void use(Ingredients used){
        ingredientsRepository.save(used);
    }

}
