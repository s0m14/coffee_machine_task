package com.coffeemachine.demo.controller;

import com.coffeemachine.demo.dto.NewReceiptDTO;
import com.coffeemachine.demo.service.CoffeeService;
import com.coffeemachine.demo.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receipt")
@Tag(name = "Receipt")
public class ReceiptController {
    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private CoffeeService coffeeService;

    @Operation(
            description = "Adds new receipt to the coffee machine system",
            summary = "Adds a new coffee receipt",
            responses = {
                    @ApiResponse(
                            description = "Successfully added new coffee receipt",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Ensure that all required fields are present and contain valid values",
                            responseCode = "400"
                    )
            }
    )
    @PostMapping("/addNewReceipt")
    public void addNewReceipt(@RequestBody NewReceiptDTO newReceiptDTO){
        coffeeService.addNewReceipt(newReceiptDTO);
    }

}
