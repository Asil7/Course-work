package uz.pdp.product.repository;

//Asilbek Fayzullayev 14.03.2022 22:37


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.product.model.Attachment;

public interface AttachmentRepository extends JpaRepository <Attachment,Integer> {

}
