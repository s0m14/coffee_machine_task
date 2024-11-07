package com.coffeemachine.demo.controller;

import com.coffeemachine.demo.dto.ClientResponseDTO;
import com.coffeemachine.demo.dto.InformationDTO;
import com.coffeemachine.demo.service.CoffeeService;
import com.coffeemachine.demo.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/coffee")
@Tag(name = "Coffee")
public class CoffeeController {
    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private CoffeeService coffeeService;

    @Operation(
            description = "Get information about coffee",
            summary = "This method provides an extensive information about specific coffee",
            responses = {
                    @ApiResponse(
                    description = "Found successfully",
                    responseCode = "200"),
                    @ApiResponse(
                            description = "Receipt is not found!",
                            responseCode = "400"
                    )
            }
    )
    @GetMapping("/getInformation/{name}")
    public ResponseEntity<InformationDTO> getInfo(@PathVariable String name){
        return new ResponseEntity<>(coffeeService.getInfo(name), HttpStatus.OK);
    }

    @Operation(
            description = "Get the most sold coffee.",
            summary = "Retrieves the most sold coffee by sorting the coffee table's in descending order on the sold_quantity column and returning the first result.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Dataset is not provided yet",
                            responseCode = "400"
                    )
            }
    )
    @GetMapping("/getPopularCoffee")
    public String coffeeServiceList(){
        return coffeeService.getPopularCoffee();
    }

    @Operation(
            description = "Order coffee",
            summary = "Order coffee by providing its name via link and it will return the result of the order.",
            responses = {
                    @ApiResponse(
                            description = "Coffee is found and successfully ordered",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Coffee is not found",
                            responseCode = "400"
                    )
            }
    )
    @GetMapping("/makeCoffee/{coffee}")
    public ResponseEntity<ClientResponseDTO> makeCoffee(@PathVariable String coffee){
        return new ResponseEntity<>(coffeeService.makeCoffee(coffee),HttpStatus.OK);
    }
}
