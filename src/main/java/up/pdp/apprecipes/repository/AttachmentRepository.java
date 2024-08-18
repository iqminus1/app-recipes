package up.pdp.apprecipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.Attachment;

import java.util.UUID;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
    default Attachment getById(UUID id) {
        return findById(id).orElseThrow(() -> NotFoundException.errorById("Attachment", id));
    }

}