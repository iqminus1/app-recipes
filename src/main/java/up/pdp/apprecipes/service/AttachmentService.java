package up.pdp.apprecipes.service;

import org.springframework.web.multipart.MultipartFile;
import up.pdp.apprecipes.dto.response.AttachmentDto;

import java.io.IOException;
import java.util.UUID;

public interface AttachmentService {
    AttachmentDto upload(MultipartFile request) throws IOException;
    AttachmentDto getById(UUID id);
    void deleteFile(UUID id) throws IOException;
}