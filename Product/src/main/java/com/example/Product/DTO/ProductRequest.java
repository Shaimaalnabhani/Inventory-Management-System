package com.example.Product.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProductRequest {
    @NotEmpty(message = "Product Name Must Not Be Empty!")
    String name;

    @Min(value = 1,message = "Price must be greater than zero !")
    double price;

    @Positive(message = "Quantity must not be negative!")
    Integer quantity;

    @NotEmpty(message = "Category is Required!")
    String category;

}
