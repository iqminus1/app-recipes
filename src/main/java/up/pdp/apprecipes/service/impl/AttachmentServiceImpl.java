package up.pdp.apprecipes.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import up.pdp.apprecipes.dto.response.AttachmentDto;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.Attachment;
import up.pdp.apprecipes.repository.AttachmentRepository;
import up.pdp.apprecipes.service.AttachmentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import static up.pdp.apprecipes.utils.AppConst.BASE_PATH;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepo;

    @Override
    public AttachmentDto upload(MultipartFile request) throws IOException {
        String originalFileName = request.getOriginalFilename();

        String fileName = UUID.randomUUID() + getExtension(request.getContentType());
        Path path = BASE_PATH.resolve(fileName);

        Attachment attachment = Attachment.builder()
                .originalName(originalFileName)
                .name(fileName)
                .type(request.getContentType())
                .size(request.getSize())
                .path(path.toString())
                .build();

        Files.createDirectories(BASE_PATH);

        try (var inputStream = request.getInputStream()) {
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        }

        Attachment savedAttachment = attachmentRepo.save(attachment);

        return new AttachmentDto(savedAttachment);
    }


    @Override
    public AttachmentDto getById(UUID id) {
        Attachment attachment = attachmentRepo.findById(id)
                .orElseThrow(() -> NotFoundException.errorById("Attachment", id));
        return new AttachmentDto(attachment);
    }

    @Override
    public void deleteFile(UUID id) throws IOException {
        Attachment attachment = attachmentRepo.findById(id)
                .orElseThrow(() -> NotFoundException.errorById("Attachment", id));
        Path path = BASE_PATH.resolve(attachment.getName());

        Files.deleteIfExists(path);

        attachmentRepo.deleteById(id);
    }

    private String getExtension(String contentType) {
        if (contentType != null) {
            return "." + contentType.substring(contentType.indexOf("/") + 1);
        }
        return ".jpg";
    }
}
