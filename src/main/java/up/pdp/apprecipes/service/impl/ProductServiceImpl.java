package up.pdp.apprecipes.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.pdp.apprecipes.dto.request.ProductCRUDDto;
import up.pdp.apprecipes.dto.response.ProductDto;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.*;
import up.pdp.apprecipes.repository.*;
import up.pdp.apprecipes.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private final ProductIngredientRepository productIngredientRepository;

    @Override
    @Transactional
    public ProductDto save(ProductCRUDDto productDto) {
        Category category = categoryRepository.findByName(productDto.getCategoryName())
                .orElseThrow(() -> NotFoundException.error("Category"));

        Attachment attachment = attachmentRepository.getById(productDto.getAttachmentId());
        User author = userRepository.getById(productDto.getAuthorId());

        // Get ingredients and validate
        Map<UUID, Long> ingredientMap = productDto.getIngredients();
        List<Ingredient> ingredients = ingredientRepository.findAllById(ingredientMap.keySet());

        if (ingredients.size() != ingredientMap.size()) {
            throw new NotFoundException("One or more ingredients not found");
        }

        List<Step> steps = stepRepository.findAllById(productDto.getStepIds());
        if (steps.size() != productDto.getStepIds().size()) {
            throw new NotFoundException("One or more steps not found");
        }

        Product product = Product.builder()
                .name(productDto.getName())
                .category(category)
                .attachment(attachment)
                .preparationTime(productDto.getPreparationTime())
                .author(author)
                .steps(steps)
                .overallRating(0D)
                .build();

        productRepository.save(product);

        // Save ProductIngredient entities
        for (Ingredient ingredient : ingredients) {
            ProductIngredient productIngredient = new ProductIngredient();
            productIngredient.setProduct(product);
            productIngredient.setIngredient(ingredient);
            productIngredient.setQuantity(ingredientMap.get(ingredient.getId()));
            productIngredientRepository.save(productIngredient);
        }

        return new ProductDto(product);
    }


    @Override
    public ProductDto getById(UUID id) {
        return new ProductDto(productRepository.getById(id));
    }

    @Override
    public List<ProductDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductDto::new)
                .toList();
    }

    @Override
    public List<ProductDto> getAllByAuthorId(UUID id) {
        userRepository.getById(id);
        return productRepository.findAllByAuthorId(id)
                .stream()
                .map(ProductDto::new)
                .toList();
    }

    @Override
    public List<ProductDto> getAllByCategoryId(UUID id) {
        return productRepository.findAllByCategoryId(id)
                .stream()
                .map(ProductDto::new)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        productRepository.delete(productRepository.getById(id));
    }

    @Override
    public List<ProductDto> getTopProductsByRating() {
        List<ProductDto> highRating = new ArrayList<>();
        List<ProductDto> lowRating = new ArrayList<>();

        List<ProductDto> all = getAll();
        for (ProductDto productDto : all){
            if (productDto.getOverallRating() >= 4.1)
                highRating.add(productDto);
            else if (productDto.getOverallRating() >= 3D) {
                lowRating.add(productDto);
            }
        }
        if (highRating.isEmpty())
            return lowRating;
        else return highRating;
    }
}
