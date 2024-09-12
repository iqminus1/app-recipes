package up.pdp.apprecipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import up.pdp.apprecipes.model.ProductIngredient;

import java.util.UUID;

public interface ProductIngredientRepository extends JpaRepository<ProductIngredient, UUID> {
}
