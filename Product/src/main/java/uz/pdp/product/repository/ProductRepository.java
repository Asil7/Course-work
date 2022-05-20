package uz.pdp.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.product.model.Cpu;
import uz.pdp.product.model.Product;
import uz.pdp.product.projection.ViewProduct;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(nativeQuery = true, value = "select p.id as id,\n" +
            "p.brand as brand,\n" +
            "           vc.name as videoCardName,\n" +
            "           vc.memory_type as memoryType,\n" +
            "           vc.amount_of_memory as amountOfMemory,\n" +
            "           c.name as cpuName,\n" +
            "           c.frequency as frequency,\n" +
            "           p.ram as ram,\n" +
            "           p.hard as hard,\n" +
            "           p.screen as screen,\n" +
            "           p.price as price,\n" +
            "           ac.attachment_id as attachmentId\n" +
            "    from products p\n" +
            "    join video_card vc on vc.id = p.video_card_id\n" +
            "    join cpu c on p.cpu_id = c.id\n" +
            "    join attachment_content ac on p.attachment_id = ac.attachment_id\n" +
            "where p.id =:id")

    Optional<ViewProduct> getAllProductById(Integer id);
}
