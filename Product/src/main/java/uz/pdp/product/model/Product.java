package uz.pdp.product.model;

//Asilbek Fayzullayev 17.05.2022 15:01   

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String brand;
    private Integer videoCardId;
    private Integer cpuId;
    private Integer ram;
    private String hard;
    private String screen;
    private double price;
    private Integer attachmentId;



}
