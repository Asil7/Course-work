package uz.pdp.cardservice.model;

//Asilbek Fayzullayev 19.05.2022 15:41   

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cardservice.model.Card;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private Integer productId;

    private String paymentIntent;


    private double totalAmount;


}
