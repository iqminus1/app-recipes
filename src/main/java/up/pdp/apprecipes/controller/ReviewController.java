package up.pdp.apprecipes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.pdp.apprecipes.dto.request.ReviewCRUDDto;
import up.pdp.apprecipes.dto.request.ReviewEmotionDto;
import up.pdp.apprecipes.dto.response.SuccessResponse;
import up.pdp.apprecipes.service.ReviewService;
import up.pdp.apprecipes.utils.AppConst;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConst.API_V1)
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ReviewCRUDDto reviewCRUDDto) {
        return ResponseEntity.ok(reviewService.save(reviewCRUDDto));
    }

    @GetMapping("/productId/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(reviewService.getAllByProductId(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        reviewService.deleteById(id);
        return ResponseEntity.ok(new SuccessResponse("Review successfully deleted"));
    }

    @PutMapping("/emotion")
    public ResponseEntity<?> emotion(@RequestBody ReviewEmotionDto reviewEmotionDto) {
        reviewService.emotion(reviewEmotionDto);
        return ResponseEntity.ok(new SuccessResponse("Review successfully emoted"));
    }
}
