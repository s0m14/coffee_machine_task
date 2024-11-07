package com.coffeemachine.demo.controller;

import com.coffeemachine.demo.dto.ResourcesDto;
import com.coffeemachine.demo.entity.Ingredients;
import com.coffeemachine.demo.service.CoffeeService;
import com.coffeemachine.demo.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ingredient")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private CoffeeService coffeeService;

    @Operation(
            description = "Get the current milk amount",
            summary = "Get the current amount of milk available in coffee machine",
            responses = {
                    @ApiResponse(
                            description = "Retrieved the milk successfully",
                            responseCode = "200"
                    )
            }
    )
    @GetMapping("/getMilk")
    public int getMilk() throws Exception{
        return ingredientService.getMilkAmount();
    }

    @Operation(
            description = "Get the current coffee beans amount",
            summary = "Get the current amount of coffee beans available in coffee machine",
            responses = {
                    @ApiResponse(
                            description = "Retrieved the coffee beans successfully",
                            responseCode = "200"
                    )
            }
    )
    @GetMapping("/getCoffeeBeans")
    public int getCoffeeBeans() throws Exception {
        return ingredientService.getCoffeeBeansAmount();
    }

    @Operation(
            description = "Get the current water amount",
            summary = "Get the current amount of water available in coffee machine",
            responses = {
                    @ApiResponse(
                            description = "Retrieved the water successfully",
                            responseCode = "200"
                    )
            }
    )
    @GetMapping("/getWater")
    public int getWater() throws Exception{
        return ingredientService.getWaterAmount();
    }

    @Operation(
            description = "Get the current availabe resources",
            summary = "Get the current available resources in coffee machine",
            responses = {
                    @ApiResponse(
                            description = "Retrieved the ingredient information successfully",
                            responseCode = "200"
                    )
            }
    )
    @GetMapping("/availableResources")
    public ResponseEntity<ResourcesDto> getAvailableResources(){
        ResourcesDto resourcesDto = ResourcesDto.builder().
                coffee_beans(ingredientService.getCoffeeBeansAmount()).
                milk(ingredientService.getMilkAmount()).
                water(ingredientService.getWaterAmount()).
                build();

        return new ResponseEntity<>(resourcesDto, HttpStatus.OK);
    }

    @Operation(
            description = "Add specified amount of coffee beans",
            summary = "Fills the coffee machine with additional coffee beans",
            responses = {
                    @ApiResponse(
                            description = "Successfully filled the coffee machine with coffee beans",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Maximum capacity is reached",
                            responseCode = "400"
                    )
            }
    )
    @PostMapping("/fillCoffeeBeans/{coffee_beans}")
    public Ingredients fillCoffeeBeans(@PathVariable int coffee_beans) throws Exception {
        return ingredientService.fillCoffeeBeans(coffee_beans);
    }

    @Operation(
            description = "Add specified amount of water",
            summary = "Fills the coffee machine with additional water",
            responses = {
        @ApiResponse(
                description = "Successfully filled the coffee machine with water",
                responseCode = "200"
        ),
        @ApiResponse(
                description = "Maximum capacity is reached",
                responseCode = "400"
        )
            }
    )
    @PostMapping("/fillWater/{water}")
    public Ingredients fillWater(@PathVariable int water) throws Exception{
        return ingredientService.fillWater(water);
    }

    @Operation(
            description = "Add specified amount of milk",
            summary = "Fills the coffee machine with additional milk",
            responses = {
        @ApiResponse(
                description = "Successfully filled the coffee machine with milk",
                responseCode = "200"
        ),
        @ApiResponse(
                description = "Maximum capacity",
                responseCode = "400"
        )
    }
    )
    @PostMapping("/fillMilk/{milk}")
    public Ingredients fillMilk(@PathVariable int milk) throws Exception {
        return ingredientService.fillMilk(milk);
    }

}
