package up.pdp.apprecipes.service;

import org.springframework.stereotype.Service;
import up.pdp.apprecipes.dto.IngredientDto;
import up.pdp.apprecipes.model.Ingredient;

import java.util.List;
import java.util.UUID;

@Service
public interface IngredientService {
    IngredientDto save(IngredientDto ingredient);
    List<IngredientDto> findAll();
    IngredientDto findById(UUID id);
    IngredientDto findByName(String name);
    void deleteById(UUID id);
}
