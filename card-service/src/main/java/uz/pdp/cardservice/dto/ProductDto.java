package uz.pdp.cardservice.dto;

//Asilbek Fayzullayev 20.05.2022 17:13   

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    Integer id;
    String brand;
    double price;
    Integer attachmentId;
}
