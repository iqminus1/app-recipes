package up.pdp.apprecipes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.pdp.apprecipes.dto.IngredientCRUDDto;
import up.pdp.apprecipes.service.IngredientService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    @PostMapping("/save")
    public ResponseEntity<?> save(IngredientCRUDDto ingredient) {
        return ResponseEntity.ok(ingredientService.save(ingredient));
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(ingredientService.findAll());
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(ingredientService.findById(id));
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        return ResponseEntity.ok(ingredientService.findByName(name));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        ingredientService.deleteById(id);
        return ResponseEntity.ok("Ingredient successfully deleted!");
    }
}
