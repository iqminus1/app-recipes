package up.pdp.apprecipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
    default Attachment getById(Integer id) {
        return findById(id).orElseThrow(() -> NotFoundException.errorById("Attachment", id));
    }

}