package up.pdp.apprecipes.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.pdp.apprecipes.dto.CategoryCrudDto;
import up.pdp.apprecipes.exceptions.AlreadyExistsException;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.Category;
import up.pdp.apprecipes.repository.CategoryRepository;
import up.pdp.apprecipes.service.CategoryService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category save(CategoryCrudDto categoryCrudDto) {
        if (categoryRepository.findByName(categoryCrudDto.getName()).isPresent())
            throw new AlreadyExistsException("Category");

        Category category = Category.builder()
                .name(categoryCrudDto.getName())
                .description(categoryCrudDto.getDescription())
                .build();

        return categoryRepository.save(category);
    }

    @Override
    public Category getById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category"));
    }

    @Override
    public Category getByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Category"));
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void delete(UUID id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category"));
        categoryRepository.deleteById(id);
    }
}
