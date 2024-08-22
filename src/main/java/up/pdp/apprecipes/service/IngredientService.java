package up.pdp.apprecipes.service;

import org.springframework.stereotype.Service;
import up.pdp.apprecipes.dto.request.IngredientCRUDDto;
import up.pdp.apprecipes.model.Ingredient;

import java.util.List;
import java.util.UUID;

@Service
public interface IngredientService {
    Ingredient save(IngredientCRUDDto ingredient);
    List<Ingredient> findAll();
    Ingredient findById(UUID id);
    Ingredient findByName(String name);
    void deleteById(UUID id);
}
