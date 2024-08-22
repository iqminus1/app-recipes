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
import up.pdp.apprecipes.dto.RatingDto;
import up.pdp.apprecipes.dto.response.SuccessResponse;
import up.pdp.apprecipes.service.RatingService;
import up.pdp.apprecipes.utils.AppConst;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConst.API_V1 + "/rating")
public class RatingController {
    private final RatingService ratingService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody RatingDto ratingDto) {
        return ResponseEntity.ok(ratingService.save(ratingDto));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(ratingService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(ratingService.getAll());
    }

    @GetMapping("/all/{raterId}")
    public ResponseEntity<?> findAllByRaterId(@PathVariable UUID raterId) {
        return ResponseEntity.ok(ratingService.getAllByRaterId(raterId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        ratingService.deleteById(id);
        return ResponseEntity.ok(new SuccessResponse("Rating successfully deleted"));
    }
}
