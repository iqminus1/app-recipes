package up.pdp.apprecipes.repository;

import io.micrometer.common.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.Step;

import java.util.UUID;

@Repository
public interface StepRepository extends JpaRepository<Step, UUID> {
    @NonNull
    default Step getById(@NonNull UUID id) {
        return findById(id).orElseThrow(() -> NotFoundException.errorById("Step", id));
    }
}
