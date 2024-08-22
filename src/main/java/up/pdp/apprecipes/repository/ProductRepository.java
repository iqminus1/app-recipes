package up.pdp.apprecipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.Product;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @NonNull
    default Product getById(@NonNull UUID id) {
        return findById(id).orElseThrow(() -> NotFoundException.errorById("Product", id));
    }

    List<Product> findAllByAuthorId(UUID id);
}
