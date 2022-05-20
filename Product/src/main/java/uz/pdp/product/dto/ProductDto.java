package uz.pdp.product.dto;

//Asilbek Fayzullayev 18.05.2022 23:58   

import lombok.Data;

@Data
public class ProductDto {
    private String brand;
    private Integer videoCardId;
    private Integer cpuId;
    private Integer ram;
    private String hard;
    private String screen;
    private double price;
    private Integer attachmentId;
}
