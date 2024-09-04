package up.pdp.apprecipes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.pdp.apprecipes.dto.request.CategoryCRUDDto;
import up.pdp.apprecipes.dto.response.SuccessResponse;
import up.pdp.apprecipes.service.CategoryService;
import up.pdp.apprecipes.utils.AppConst;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConst.API_V1 + "/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CategoryCRUDDto categoryCRUDDto) {
        return ResponseEntity.ok(categoryService.save(categoryCRUDDto));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        return ResponseEntity.ok(categoryService.getByName(name));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        categoryService.delete(id);
        return ResponseEntity.ok(new SuccessResponse("Category successfully deleted"));
    }
}
