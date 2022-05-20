package uz.pdp.cardservice.model;

//Asilbek Fayzullayev 18.05.2022 15:15   

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer productId;
    private Integer userId;
    @Enumerated(EnumType.STRING)
    private Status status=Status.NEW;
    private Integer quantity;
    private double price;

    @OrderBy
    @CreationTimestamp
    @Column(nullable = false, updatable = false, columnDefinition = " timestamp default now() ")
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
}
