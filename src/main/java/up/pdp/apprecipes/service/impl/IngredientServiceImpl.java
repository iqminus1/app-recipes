package up.pdp.apprecipes.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.pdp.apprecipes.dto.request.IngredientCRUDDto;
import up.pdp.apprecipes.dto.response.IngredientDto;
import up.pdp.apprecipes.exceptions.AlreadyExistsException;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.Attachment;
import up.pdp.apprecipes.model.Ingredient;
import up.pdp.apprecipes.repository.AttachmentRepository;
import up.pdp.apprecipes.repository.CategoryRepository;
import up.pdp.apprecipes.repository.IngredientRepository;
import up.pdp.apprecipes.service.IngredientService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public IngredientDto save(IngredientCRUDDto ingredientCRUDDto) {
        if (ingredientRepository.findByName(ingredientCRUDDto.getName()).isPresent()){
            throw new AlreadyExistsException(ingredientCRUDDto.getName());
        }
        Attachment attachment = attachmentRepository.getById(ingredientCRUDDto.getAttachmentId());
        return new IngredientDto(ingredientRepository.save(new Ingredient(ingredientCRUDDto.getName(), attachment)));
    }

    @Override
    public List<IngredientDto> findAll() {
        return ingredientRepository.findAll()
                .stream()
                .map(IngredientDto::new)
                .toList();
    }

    @Override
    public IngredientDto findById(UUID id) {
        return new IngredientDto(ingredientRepository.findById(id)
                .orElseThrow(() -> NotFoundException.errorById("Ingredient", id)));
    }

    @Override
    public IngredientDto findByName(String name) {
        return new IngredientDto(ingredientRepository.findByName(name)
                .orElseThrow(() -> NotFoundException.error("Ingredient")));
    }

    @Override
    public void deleteById(UUID id) {
        ingredientRepository.deleteById(id);
    }
}
