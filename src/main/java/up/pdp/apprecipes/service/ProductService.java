package up.pdp.apprecipes.service;

import up.pdp.apprecipes.model.Category;
import up.pdp.apprecipes.model.Rating;
import up.pdp.apprecipes.model.enums.TimeFilter;
import up.pdp.apprecipes.dto.ProductCrudDto;
import up.pdp.apprecipes.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product save(ProductCrudDto productCrudDto);
    Product getById(UUID id);
    List<Product> getByAuthorId(UUID id);
    List<Product> getAll();
    List<Product> getAllBySpec(TimeFilter timeFilter, Category category, Rating rating);
    void delete(UUID id);
}
