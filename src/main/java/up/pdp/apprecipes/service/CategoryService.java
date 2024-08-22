package up.pdp.apprecipes.service;

import up.pdp.apprecipes.dto.CategoryDto;

import java.util.List;
import java.util.UUID;


public interface CategoryService {
    CategoryDto save(CategoryDto category);
    CategoryDto getById(UUID id);
    CategoryDto getByName(String name);
    List<CategoryDto> getAll();
    void delete(UUID id);
}
