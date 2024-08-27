package up.pdp.apprecipes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.pdp.apprecipes.dto.ProductDto;
import up.pdp.apprecipes.dto.response.SuccessResponse;
import up.pdp.apprecipes.repository.ProductRepository;
import up.pdp.apprecipes.service.ProductService;
import up.pdp.apprecipes.specification.ProductSpecification;
import up.pdp.apprecipes.utils.AppConst;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConst.API_V1 + "/product")
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProductDto productDto) {
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        productService.delete(id);
        return ResponseEntity.ok(new SuccessResponse("Product successfully deleted"));
    }
    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody ProductSpecification productSpecification){
        return (ResponseEntity<?>) productRepository.findAll((Pageable) productSpecification);
    }
}
