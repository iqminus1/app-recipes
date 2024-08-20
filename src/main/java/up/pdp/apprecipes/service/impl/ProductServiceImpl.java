package up.pdp.apprecipes.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import up.pdp.apprecipes.model.*;
import org.springframework.stereotype.Service;
import up.pdp.apprecipes.dto.ProductCrudDto;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.enums.TimeFilter;
import up.pdp.apprecipes.repository.AttachmentRepository;
import up.pdp.apprecipes.repository.CategoryRepository;
import up.pdp.apprecipes.repository.IngredientRepository;
import up.pdp.apprecipes.repository.ProductRepository;
import up.pdp.apprecipes.repository.StepRepository;
import up.pdp.apprecipes.repository.UserRepository;
import up.pdp.apprecipes.service.ProductService;
import up.pdp.apprecipes.specification.ProductSpecification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final CategoryRepository categoryRepository;
    private final AttachmentRepository attachmentRepository;
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;
    private final StepRepository stepRepository;
    private final ProductRepository productRepository;

    @Override
    public Product save(ProductCrudDto productCrudDto) {
        Category category = categoryRepository.findById(productCrudDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category"));

        Attachment attachment = attachmentRepository.findById(productCrudDto.getAttachmentId())
                .orElseThrow(() -> new NotFoundException("Attachment"));

        User author = userRepository.findById(productCrudDto.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author"));

        List<Ingredient> ingredients = ingredientRepository.findAllById(productCrudDto.getIngredientIds());

        List<Step> steps = stepRepository.findAllById(productCrudDto.getStepIds());

        Product product = Product.builder()
                .name(productCrudDto.getName())
                .category(category)
                .attachment(attachment)
                .preparationTime(productCrudDto.getPreparationTime())
                .ingredients(ingredients)
                .author(author)
                .steps(steps)
                .build();

        return productRepository.save(product);
    }

    @Override
    public Product getById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product"));
    }

    @Override
    public List<Product> getByAuthorId(UUID id) {
        userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author"));
        return productRepository.findByAuthorId(id);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllBySpec(TimeFilter timeFilter, Category category, Rating rating) {
        Specification<Product> spec = Specification.where(ProductSpecification.byCategory(category))
                .and(ProductSpecification.byRating(rating))
                .and(ProductSpecification.filterTime(timeFilter));
        return productRepository.findAll(spec);
    }

    @Override
    public void delete(UUID id) {
        productRepository.deleteById(id);
    }
}
