package com.example.Product.Services;


import com.example.Product.DTO.ProductRequest;
import com.example.Product.Entity.Product;
import com.example.Product.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product save(Product product){
        product.setCreatedDate(new Date());
        product.setIsActive(Boolean.TRUE);

        return productRepository.save(product);
    }

    public Product updateProduct(int id, ProductRequest request) throws Exception {
        Product product = getProductById(id);

        product.setName(request.getName());
        product.setCategory(request.getCategory());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setUpdatedDate(new Date());

        return productRepository.save(product);
    }


    public void deleteProduct(Integer id) throws Exception {
        Product existingProduct = productRepository.findById(id).get();
        if (existingProduct != null && existingProduct.getIsActive()) {
            existingProduct.setUpdatedDate(new Date());
            existingProduct.setIsActive(Boolean.FALSE);
            productRepository.save(existingProduct);
        } else {
            throw new Exception("BAD REQUEST");
        }
    }

    public List<Product> getActiveProducts(){
        List<Product> allProducts = productRepository.findAll();
        List<Product>activeProduct= new ArrayList<>();
        for (Product p: allProducts){
            if (p.getIsActive()==true){
                activeProduct.add(p);
            }
        }
        return activeProduct;
    }

    public Product getProductById(Integer id) throws Exception{
        Product existingProduct = productRepository.findById(id).get();
        if (existingProduct != null && existingProduct.getIsActive()){
            return existingProduct;
        } else {
            throw new Exception("BAD REQUEST");
        }
    }
}
