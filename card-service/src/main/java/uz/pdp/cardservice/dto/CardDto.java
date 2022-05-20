package uz.pdp.cardservice.dto;

//Asilbek Fayzullayev 20.05.2022 16:38   

import com.sun.source.doctree.IndexTree;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardDto {
    Integer id;
    Integer productId;
    String brand;
    Integer quantity;
    double price;
    Integer attachmentId;

}

