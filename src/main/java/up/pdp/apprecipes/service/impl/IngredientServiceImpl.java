package up.pdp.apprecipes.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.pdp.apprecipes.dto.request.IngredientCRUDDto;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.mapper.DefaultMapper;
import up.pdp.apprecipes.model.Ingredient;
import up.pdp.apprecipes.repository.AttachmentRepository;
import up.pdp.apprecipes.repository.IngredientRepository;
import up.pdp.apprecipes.service.IngredientService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final AttachmentRepository attachmentRepository;
    private final DefaultMapper defaultMapper;

    @Override
    public Ingredient save(IngredientCRUDDto ingredient) {
        Ingredient ingredient1 = new Ingredient();
        defaultMapper.updateEntity(ingredient, ingredient1);
        ingredient1.setAttachment(attachmentRepository.getById(ingredient.getAttachmentId()));
        return ingredientRepository.save(ingredient1);
    }

    @Override
    public List<Ingredient> findAll() {
        List<Ingredient> all = ingredientRepository.findAll();
        if (all.isEmpty()) throw new NotFoundException("ingredient not found!");
        return all;
    }

    @Override
    public Ingredient findById(UUID id) {
        return ingredientRepository.findById(id).orElseThrow(() -> new NotFoundException("ingredient not found!"));
    }

    @Override
    public Ingredient findByName(String name) {
        return ingredientRepository.findByName(name).orElseThrow(() -> new NotFoundException("ingredient not found!"));
    }

    @Override
    public void deleteById(UUID id) {
        ingredientRepository.deleteById(id);
    }
}
