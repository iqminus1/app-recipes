package up.pdp.apprecipes.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.pdp.apprecipes.dto.request.ProductCRUDDto;
import up.pdp.apprecipes.dto.response.ProductDto;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.Attachment;
import up.pdp.apprecipes.model.Category;
import up.pdp.apprecipes.model.Ingredient;
import up.pdp.apprecipes.model.Product;
import up.pdp.apprecipes.model.Step;
import up.pdp.apprecipes.model.User;
import up.pdp.apprecipes.repository.AttachmentRepository;
import up.pdp.apprecipes.repository.CategoryRepository;
import up.pdp.apprecipes.repository.IngredientRepository;
import up.pdp.apprecipes.repository.ProductRepository;
import up.pdp.apprecipes.repository.StepRepository;
import up.pdp.apprecipes.repository.UserRepository;
import up.pdp.apprecipes.service.ProductService;

import java.util.ArrayList;
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
    @Transactional
    public ProductDto save(ProductCRUDDto productDto) {
        Category category = categoryRepository.getById(productDto.getCategoryId());
        Attachment attachment = attachmentRepository.getById(productDto.getAttachmentId());
        User author = userRepository.getById(productDto.getAuthorId());

        List<Ingredient> ingredients = ingredientRepository.findAllById(productDto.getIngredientIds());
        List<Step> steps = stepRepository.findAllById(productDto.getStepIds());

        if (ingredients.size() != productDto.getIngredientIds().size()) {
            throw new NotFoundException("One or more ingredients not found");
        }
        if (steps.size() != productDto.getStepIds().size()) {
            throw new NotFoundException("One or more steps not found");
        }

        Product product = Product.builder()
                .name(productDto.getName())
                .category(category)
                .attachment(attachment)
                .preparationTime(productDto.getPreparationTime())
                .ingredients(ingredients)
                .author(author)
                .steps(steps)
                .overallRating(0D)
                .build();

        return new ProductDto(productRepository.save(product));
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
