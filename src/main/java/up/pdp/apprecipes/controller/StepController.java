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
import up.pdp.apprecipes.dto.request.StepCRUDDto;
import up.pdp.apprecipes.dto.response.SuccessResponse;
import up.pdp.apprecipes.service.StepService;
import up.pdp.apprecipes.utils.AppConst;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConst.API_V1 + "/step")
public class StepController {
    private final StepService stepService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody StepCRUDDto stepDto) {
        return ResponseEntity.ok(stepService.save(stepDto));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(stepService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(stepService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        stepService.deleteById(id);
        return ResponseEntity.ok(new SuccessResponse("Step successfully deleted"));
    }
}
