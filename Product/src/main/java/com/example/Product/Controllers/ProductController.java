package com.example.Product.Controllers;

import com.example.Product.DTO.ProductRequest;
import com.example.Product.Entity.Product;
import com.example.Product.Services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")

public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/CreateProduct")
    public String createProduct(@Valid @RequestBody ProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .category(request.getCategory())
                .price(request.getPrice())
                .quantity(request.getQuantity())

                .build();
        productService.save(product);
        return "Added Product with ID: " + product.getId();
    }

    @GetMapping("/getAllProduct")
    public List<Product> getAllProduct(){
        List<Product> products= productService.getAllProducts();
        return products;
    }

    @GetMapping("/getActiveProduct")
    public List<Product> getActiveProduct(){
    List<Product> products= productService.getActiveProducts();
    return products;
    }

    @GetMapping("/getById/{id}")
    public Product getProductById(@PathVariable int id) throws Exception{
        return productService.getProductById(id);
    }

    @PutMapping("/update/{id}")
    public Product updateProduct( @PathVariable int id , @Valid @RequestBody ProductRequest request) throws Exception{
        return productService.updateProduct(id,request);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) throws Exception{
        productService.deleteProduct(id);
        return "Success";
    }
}
