package up.pdp.apprecipes.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.pdp.apprecipes.dto.IngredientDto;
import up.pdp.apprecipes.exceptions.AlreadyExistsException;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.mapper.DefaultMapper;
import up.pdp.apprecipes.model.Ingredient;
import up.pdp.apprecipes.repository.AttachmentRepository;
import up.pdp.apprecipes.repository.IngredientRepository;
import up.pdp.apprecipes.service.IngredientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final AttachmentRepository attachmentRepository;
    private final DefaultMapper defaultMapper;

    @Override
    public IngredientDto save(IngredientDto ingredient) {
        Ingredient byName = ingredientRepository.findByName(ingredient.getName()).orElse(null);
        if (byName != null) {
            throw new AlreadyExistsException("Ingredient");
        }
        Ingredient ingredient1 = new Ingredient();
        defaultMapper.ingredientUpdateEntity(ingredient, ingredient1);
        ingredient1.setAttachment(attachmentRepository.getById(ingredient.getAttachmentId()));
        ingredientRepository.save(ingredient1);
        return ingredient;
    }

    @Override
    public List<IngredientDto> findAll() {
        return ingredientRepository.findAll().stream().map(IngredientDto::new).toList();
    }

    @Override
    public IngredientDto findById(UUID id) {
        return new IngredientDto(ingredientRepository.findById(id).orElseThrow(() -> new NotFoundException("Ingredient not found with this ID: ------->>>>>>" + id)));
    }

    @Override
    public IngredientDto findByName(String name) {
        return new IngredientDto(ingredientRepository.findByName(name).orElseThrow(() -> new NotFoundException("Ingredient not found")));
    }

    @Override
    public void deleteById(UUID id) {
        ingredientRepository.deleteById(id);
    }
}
