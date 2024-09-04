package up.pdp.apprecipes.service;

import up.pdp.apprecipes.dto.request.CategoryCRUDDto;
import up.pdp.apprecipes.dto.response.CategoryDto;

import java.util.List;
import java.util.UUID;


public interface CategoryService {
    CategoryDto save(CategoryCRUDDto category);
    CategoryDto getById(UUID id);
    CategoryDto getByName(String name);
    List<CategoryDto> getAll();
    void delete(UUID id);
}
