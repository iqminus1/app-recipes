package up.pdp.apprecipes.service;

import up.pdp.apprecipes.dto.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDto save(ProductDto productDto);
    ProductDto getById(UUID id);
    List<ProductDto> getAll();
    List<ProductDto> getAllByAuthorId(UUID id);
    List<ProductDto> getAllByCategoryId(UUID id);
    void delete(UUID id);
    List<ProductDto> getTopProductsByRating();
}
