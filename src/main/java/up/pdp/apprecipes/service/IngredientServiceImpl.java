package up.pdp.apprecipes.service;

import org.springframework.stereotype.Service;
import up.pdp.apprecipes.dto.IngredientCRUDDto;
import up.pdp.apprecipes.model.Ingredient;

import java.util.List;
import java.util.UUID;

@Service
public class IngredientServiceImpl implements IngredientService {
    @Override
    public Ingredient save(IngredientCRUDDto ingredient) {
        return null;
    }

    @Override
    public List<Ingredient> findAll() {
        return List.of();
    }

    @Override
    public Ingredient findById(UUID id) {
        return null;
    }

    @Override
    public Ingredient findByName(String name) {
        return null;
    }

    @Override
    public Ingredient deleteById(UUID id) {
        return null;
    }
}
