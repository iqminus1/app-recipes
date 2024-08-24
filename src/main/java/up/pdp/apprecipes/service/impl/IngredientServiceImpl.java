package up.pdp.apprecipes.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.pdp.apprecipes.dto.IngredientDto;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.mapper.DefaultMapper;
import up.pdp.apprecipes.model.Ingredient;
import up.pdp.apprecipes.repository.AttachmentRepository;
import up.pdp.apprecipes.repository.IngredientRepository;
import up.pdp.apprecipes.service.IngredientService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final AttachmentRepository attachmentRepository;
    private final DefaultMapper defaultMapper;

    @Override
    public IngredientDto save(IngredientDto ingredient) {
        Ingredient ingredient1 = new Ingredient();
        defaultMapper.ingredientUpdateEntity(ingredient, ingredient1);
        ingredient1.setAttachment(attachmentRepository.getById(ingredient.getAttachmentId()));
        ingredientRepository.save(ingredient1);
        return ingredient;
    }

    @Override
    public List<IngredientDto> findAll() {
        List<IngredientDto> ingredients = new ArrayList<>();
        defaultMapper.ingredientsEntityToDto(ingredientRepository.getAll(), ingredients);
        return ingredients;
    }

    @Override
    public IngredientDto findById(UUID id) {
        IngredientDto ingredientDto = new IngredientDto();
        defaultMapper.ingredientEntityToDto(ingredientRepository.getById(id),ingredientDto); ;
        return ingredientDto;
    }

    @Override
    public IngredientDto findByName(String name) {
        IngredientDto ingredientDto = new IngredientDto();
        defaultMapper.ingredientEntityToDto(ingredientRepository.getByName(name), ingredientDto);
        return ingredientDto;
    }

    @Override
    public void deleteById(UUID id) {
        ingredientRepository.deleteById(id);
    }
}
