package up.pdp.apprecipes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import up.pdp.apprecipes.dto.response.AttachmentDto;
import up.pdp.apprecipes.service.AttachmentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attachment")
public class AttachmentController {
    private final AttachmentService attachmentService;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> loadImage(@PathVariable UUID id) {
        AttachmentDto attachmentDto = attachmentService.getById(id);
        Path path = Path.of(attachmentDto.getPath());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(attachmentDto.getType()));
        headers.setContentDisposition(ContentDisposition.attachment()
                .filename(attachmentDto.getOriginalName())
                .build());

        try {
            InputStreamResource resource = new InputStreamResource(Files.newInputStream(path));
            return ResponseEntity.ok().headers(headers).body(resource);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }
    }


    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AttachmentDto> upload(@RequestParam("file") MultipartFile file) {
        try {
            AttachmentDto attachmentDto = attachmentService.upload(file);
            return new ResponseEntity<>(attachmentDto, HttpStatus.CREATED);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAttachment(@PathVariable UUID id) {
        try {
            attachmentService.deleteFile(id);
            return ResponseEntity.noContent().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
