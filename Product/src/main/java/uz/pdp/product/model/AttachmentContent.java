package uz.pdp.product.model;

//Asilbek Fayzullayev 14.03.2022 17:08

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "attachment_content")
public class AttachmentContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    byte[] data;
    @OneToOne(cascade = CascadeType.ALL)
    private Attachment attachment;

    public AttachmentContent(byte[] data, Attachment attachment) {
        this.data = data;
        this.attachment = attachment;
    }
}
