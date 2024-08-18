package up.pdp.apprecipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import up.pdp.apprecipes.model.Step;

import java.util.UUID;

public interface StepRepository extends JpaRepository<Step, UUID> {
}
