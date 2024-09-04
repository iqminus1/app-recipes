package up.pdp.apprecipes.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.pdp.apprecipes.dto.request.CategoryCRUDDto;
import up.pdp.apprecipes.dto.response.CategoryDto;
import up.pdp.apprecipes.exceptions.AlreadyExistsException;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.Category;
import up.pdp.apprecipes.repository.CategoryRepository;
import up.pdp.apprecipes.service.CategoryService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto save(CategoryCRUDDto categoryCRUDDto) {
        if (categoryRepository.findByName(categoryCRUDDto.getName()).isPresent())
            throw new AlreadyExistsException("Category with name " + categoryCRUDDto.getName());

        Category category = Category.builder()
                .name(categoryCRUDDto.getName())
                .description(categoryCRUDDto.getDescription())
                .build();

        return new CategoryDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto getById(UUID id) {
        return new CategoryDto(categoryRepository.getById(id));
    }

    @Override
    public CategoryDto getByName(String name) {
        return new CategoryDto(categoryRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Category with name " + name + " not found")));
    }

    @Override
    public List<CategoryDto> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        categoryRepository.delete(categoryRepository.getById(id));
    }
}
