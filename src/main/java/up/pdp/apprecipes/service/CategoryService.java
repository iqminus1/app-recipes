package up.pdp.apprecipes.service;

import up.pdp.apprecipes.model.Category;

import java.util.List;
import java.util.UUID;


public interface CategoryService {
    Category save(Category category);
    Category getById(UUID id);
    Category getByName(String name);
    List<Category> getAll();
    void delete(Category category);
}
