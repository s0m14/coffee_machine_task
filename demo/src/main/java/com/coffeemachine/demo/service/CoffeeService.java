package com.coffeemachine.demo.service;

import com.coffeemachine.demo.dto.ClientResponseDTO;
import com.coffeemachine.demo.dto.InformationDTO;
import com.coffeemachine.demo.dto.NewReceiptDTO;
import com.coffeemachine.demo.entity.CoffeeReceipt;
import com.coffeemachine.demo.entity.Ingredients;
import com.coffeemachine.demo.exception.RequestException;
import com.coffeemachine.demo.repository.CoffeeRecipeRepository;
import com.coffeemachine.demo.repository.InstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class CoffeeService {
    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private CoffeeRecipeRepository coffeeRecipeRepository;

    @Autowired
    private InstructionRepository instructionRepository;

    public ClientResponseDTO makeCoffee(String name){
        CoffeeReceipt coffeeReceipt = coffeeRecipeRepository.findByName(name).orElseThrow(() -> new RequestException("Coffee receipt is not found"));

        int coffee_beans = ingredientService.getCoffeeBeansAmount();
        int water = ingredientService.getWaterAmount();
        int milk = ingredientService.getMilkAmount();

        int rcoffee_beans = coffeeReceipt.getCoffee_beans();
        int rwater = coffeeReceipt.getWater();
        int rmilk = coffeeReceipt.getMilk();

        if(ingredientService.isOk() && (coffee_beans - rcoffee_beans >= 0) && (water - rwater >= 0) && (milk - rmilk >= 0)){
            ClientResponseDTO responseDTO = ClientResponseDTO.builder().
                    coffee_type(coffeeReceipt.getName()).
                    message("Enjoy your drink!").
                    build();

            ingredientService.use(new Ingredients(1000,coffee_beans - rcoffee_beans,water - rwater,milk - rmilk));
            coffeeReceipt.setSold(coffeeReceipt.getSold() + 1);
            coffeeRecipeRepository.save(coffeeReceipt);
            return responseDTO;
        }else{
            throw new RequestException("Something went wrong. Please, check resources!");
        }
    }

    public void addNewReceipt(@RequestBody NewReceiptDTO newReceiptDTO){
        if(newReceiptDTO.getName() != null && newReceiptDTO.getInstruction() != null) {
            CoffeeReceipt coffeeReceipt = CoffeeReceipt.builder().
                    name(newReceiptDTO.getName()).
                    coffee_beans(newReceiptDTO.getCoffee_beans()).
                    water(newReceiptDTO.getWater()).
                    milk(newReceiptDTO.getMilk()).
                    sugar(newReceiptDTO.isSugar()).
                    instructions(newReceiptDTO.getInstruction()).
                    build();

            coffeeRecipeRepository.save(coffeeReceipt);
        }else{
            throw new RequestException("Specify the name and instructions!");
        }
    }

    @Cacheable("info")
    public InformationDTO getInfo(String name){
        CoffeeReceipt coffeeReceipt = coffeeRecipeRepository.findByName(name).orElseThrow(() -> new RequestException(name + " receipt is not found!"));
        return InformationDTO.builder().
                name(coffeeReceipt.getName()).
                coffee_beans(coffeeReceipt.getCoffee_beans()).
                water(coffeeReceipt.getWater()).
                milk(coffeeReceipt.getMilk()).
                sugar(coffeeReceipt.isSugar()).
                sold_quantity(coffeeReceipt.getSold()).
                instruction(coffeeReceipt.getInstructions()).
                build();
    }

    public String getPopularCoffee(){
        if(!coffeeRecipeRepository.getPopularCoffee().isEmpty()){
            return coffeeRecipeRepository.getPopularCoffee().getFirst();
        }else{
            throw new RequestException("No data available for now!");
        }
    }

}
