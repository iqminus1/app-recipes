package up.pdp.apprecipes.repository;

import io.micrometer.common.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.Attachment;

import java.util.UUID;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
    @NonNull
    default Attachment getById(@NonNull UUID id) {
        return findById(id).orElseThrow(() -> NotFoundException.errorById("Attachment", id));
    }
}