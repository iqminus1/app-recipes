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
import up.pdp.apprecipes.dto.request.IngredientCRUDDto;
import up.pdp.apprecipes.dto.response.SuccessResponse;
import up.pdp.apprecipes.service.IngredientService;
import up.pdp.apprecipes.utils.AppConst;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConst.API_V1 + "/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody IngredientCRUDDto ingredient) {
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
        return ResponseEntity.ok(new SuccessResponse("Ingredient successfully deleted"));
    }
}
