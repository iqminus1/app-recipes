package up.pdp.apprecipes.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.Ingredient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {
    Optional<Ingredient> findByName(String name);
    @NotNull
    default Ingredient getById(@NotNull UUID uuid){
        return findById(uuid).orElseThrow(() -> new NotFoundException("Ingredient not found"));
    }
    @NotNull
    default Ingredient getByName(@NotBlank String name){
        return findByName(name).orElseThrow(() -> new NotFoundException("Ingredient not found"));
    }
    @NotNull
    default List<Ingredient> getAll(){
            return findAll();
    }
}
