package uz.pdp.product.controller;

//Asilbek Fayzullayev 18.05.2022 16:44   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.product.common.ApiResponse;
import uz.pdp.product.dto.ProductDto;
import uz.pdp.product.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public HttpEntity<?> getAllProduct() {
        ApiResponse allProduct = productService.getAllProduct();
        return ResponseEntity.status(allProduct.isSuccess() ? 200 : 400).body(allProduct);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getProductById(@PathVariable Integer id) {
        ApiResponse productById = productService.getProductById(id);
        return ResponseEntity.status(productById.isSuccess() ? 200 : 400).body(productById.getObject());
    }

    @PostMapping
    public HttpEntity<?> addProduct(@RequestBody ProductDto productDto) {
        ApiResponse apiResponse = productService.addProduct(productDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProduct(@PathVariable Integer id){
        ApiResponse apiResponse = productService.deleteById(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }
}
