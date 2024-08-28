package up.pdp.apprecipes.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.pdp.apprecipes.service.AttachmentService;
import up.pdp.apprecipes.utils.AppConst;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConst.API_V1 + "/attachment")
public class AttachmentController {
    private final AttachmentService attachmentService;

    @GetMapping("/read/{id}")
    public void read(@PathVariable UUID id, HttpServletResponse resp) {
        attachmentService.read(resp, id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(HttpServletRequest req) {
        return ResponseEntity.status(200).body(attachmentService.create(req));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(HttpServletRequest req, @PathVariable UUID id) {
        return ResponseEntity.status(200).body(attachmentService.update(req, id));
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id) {
        attachmentService.delete(id);
    }
}
