package uz.pdp.product.service;

//Asilbek Fayzullayev 17.05.2022 23:52   

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.product.common.ApiResponse;
import uz.pdp.product.dto.ProductDto;
import uz.pdp.product.model.Cpu;
import uz.pdp.product.model.Product;
import uz.pdp.product.model.VideoCard;
import uz.pdp.product.projection.ViewProduct;
import uz.pdp.product.repository.CpuRepository;
import uz.pdp.product.repository.ProductRepository;
import uz.pdp.product.repository.VideoCardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CpuRepository cpuRepository;
    @Autowired
    VideoCardRepository videoCardRepository;

    public ApiResponse getAllProduct() {
        List<Product> all = productRepository.findAll();
        if (all.size() == 0) {
            return new ApiResponse("List empty", false);
        }
        return new ApiResponse("Success", true, all);
    }

    public ApiResponse getProductById(Integer id) {
        Optional<ViewProduct> optionalProduct = productRepository.getAllProductById(id);
        if (!optionalProduct.isPresent()) {
            return new ApiResponse("Product not found", false);
        }
        return new ApiResponse("Success", true, optionalProduct.get());
    }

    public ApiResponse deleteById(Integer id) {
        try {
            productRepository.deleteById(id);
            return new ApiResponse("Successfully deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Product not found", false);
        }
    }

    public ApiResponse addProduct(ProductDto productDto) {
        try {
            Product product = new Product();
            product.setBrand(productDto.getBrand());
            product.setVideoCardId(productDto.getVideoCardId());
            product.setCpuId(productDto.getCpuId());
            product.setRam(productDto.getRam());
            product.setHard(productDto.getHard());
            product.setScreen(productDto.getScreen());
            product.setPrice(productDto.getPrice());
            product.setAttachmentId(productDto.getAttachmentId());
            Product save = productRepository.save(product);
            return new ApiResponse("Successfully saved", true, save);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }


}
