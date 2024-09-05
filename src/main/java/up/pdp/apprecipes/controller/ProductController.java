package up.pdp.apprecipes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import up.pdp.apprecipes.dto.request.ProductCRUDDto;
import up.pdp.apprecipes.dto.response.SuccessResponse;
import up.pdp.apprecipes.model.Category;
import up.pdp.apprecipes.model.Product;
import up.pdp.apprecipes.model.Rating;
import up.pdp.apprecipes.model.enums.TimeFilter;
import up.pdp.apprecipes.repository.CategoryRepository;
import up.pdp.apprecipes.repository.ProductRepository;
import up.pdp.apprecipes.service.ProductService;
import up.pdp.apprecipes.service.RatingService;
import up.pdp.apprecipes.specification.ProductSpecification;
import up.pdp.apprecipes.utils.AppConst;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConst.API_V1 + "/product")
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final RatingService ratingService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProductCRUDDto productDto) {
        return ResponseEntity.ok(productService.save(productDto));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/all/{authorId}")
    public ResponseEntity<?> findAllByAuthorId(@PathVariable UUID authorId) {
        return ResponseEntity.ok(productService.getAllByAuthorId(authorId));
    }
    @GetMapping("/product-by-rating")
    public ResponseEntity<?> findProductByRating() {
        return ResponseEntity.ok(productService.getTopProductsByRating());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        productService.delete(id);
        return ResponseEntity.ok(new SuccessResponse("Product successfully deleted"));
    }
    @GetMapping("/filter")
    public ResponseEntity<Page<Product>> filter(
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) Double ratingNum,
            @RequestParam(required = false) TimeFilter timeFilter,
            Pageable pageable) {


        Specification<Product> specification = Specification.where(null);

        if (!categoryName.isBlank()) {
            Optional<Category> category = categoryRepository.findByName(categoryName);
            if (category.isPresent()) {
                specification = specification.and(ProductSpecification.byCategory(category.get()));
            }
        }
        if (ratingNum != null) {
            List<Rating> byRating = ratingService.getByRating(ratingNum);
            if (!byRating.isEmpty())
                specification = specification.and(ProductSpecification.byRating(byRating.get(0)));
        }
        if (timeFilter != null) {
            specification = specification.and(ProductSpecification.filterTime(timeFilter));
        }

        Page<Product> products = productRepository.findAll(specification, pageable);
        return ResponseEntity.ok(products);
    }
}
