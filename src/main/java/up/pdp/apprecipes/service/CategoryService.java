package up.pdp.apprecipes.service;

import up.pdp.apprecipes.dto.CategoryCrudDto;
import up.pdp.apprecipes.model.Category;

import java.util.List;
import java.util.UUID;


public interface CategoryService {
    Category save(CategoryCrudDto category);
    Category getById(UUID id);
    Category getByName(String name);
    List<Category> getAll();
    void delete(UUID id);
}
