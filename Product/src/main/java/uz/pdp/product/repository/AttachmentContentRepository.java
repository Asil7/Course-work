package uz.pdp.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.product.model.AttachmentContent;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {

    AttachmentContent findByAttachmentId(Integer attachment_id);
}
