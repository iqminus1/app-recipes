package up.pdp.apprecipes.service;

import org.springframework.stereotype.Service;
import up.pdp.apprecipes.dto.request.IngredientCRUDDto;
import up.pdp.apprecipes.dto.response.IngredientDto;

import java.util.List;
import java.util.UUID;

@Service
public interface IngredientService {
    IngredientDto save(IngredientCRUDDto ingredient);
    List<IngredientDto> findAll();
    IngredientDto findById(UUID id);
    IngredientDto findByName(String name);
    void deleteById(UUID id);
}
