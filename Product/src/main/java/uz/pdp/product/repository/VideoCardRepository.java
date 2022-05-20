package uz.pdp.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.product.model.Cpu;
import uz.pdp.product.model.VideoCard;

public interface VideoCardRepository extends JpaRepository<VideoCard,Integer> {
}
