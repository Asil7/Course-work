package uz.pdp.product.model;

//Asilbek Fayzullayev 14.03.2022 17:05

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
@Entity(name = "attachments")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Long size;
    private String contentType;


    public Attachment(String name, String contentType, Long size) {
        this.name = name;
        this.size = size;
        this.contentType = contentType;
    }
}
