package up.pdp.apprecipes.service.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.pdp.apprecipes.dto.ApiResultDto;
import up.pdp.apprecipes.dto.AttachmentDto;
import up.pdp.apprecipes.mapper.DefaultMapper;
import up.pdp.apprecipes.model.Attachment;
import up.pdp.apprecipes.repository.AttachmentRepository;
import up.pdp.apprecipes.service.AttachmentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import static up.pdp.apprecipes.utils.AppConst.BASE_PATH;


@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final DefaultMapper defaultMapper;

    @Override
    public void read(HttpServletResponse resp, UUID id) {
        Attachment attachment = attachmentRepository.getById(id);
        try {
            Path path = Path.of(attachment.getPath());
            Files.copy(path, resp.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ApiResultDto<?> create(HttpServletRequest req) {
        try {
            List<AttachmentDto> result = req.getParts().stream()
                    .map(part -> createOrUpdate(new Attachment(), part, false))
                    .toList();
            return ApiResultDto.success(result);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public ApiResultDto<?> update(HttpServletRequest req, UUID id) {
        try {
            Attachment attachment = attachmentRepository.getById(id);
            List<AttachmentDto> result = req.getParts().stream()
                    .map(part -> createOrUpdate(attachment, part, true)).toList();
            return ApiResultDto.success(result);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(UUID id) {
        Attachment attachment = attachmentRepository.getById(id);
        attachmentRepository.delete(attachment);
    }

    private AttachmentDto createOrUpdate(Attachment attachment, Part part, boolean isUpdate) {
        if (isUpdate) {
            Attachment copyAttachment = new Attachment(
                    attachment.getName(),
                    attachment.getOriginalName(),
                    attachment.getPath(),
                    attachment.getContentType(),
                    attachment.getSize()
            );
            copyAttachment.setDeleted(true);
            attachmentRepository.save(copyAttachment);

        }
        try {

            String contentType = part.getContentType();
            String originalName = part.getSubmittedFileName();
            long size = part.getSize();

            String[] split = originalName.split("\\.");
            String s = split[split.length - 1];
            UUID uuid = UUID.randomUUID();

            String name = uuid + "." + s;

            String pathString = BASE_PATH + "/" + name;

            Files.copy(part.getInputStream(), Path.of(pathString));

            attachment.setName(name);
            attachment.setSize(size);
            attachment.setPath(pathString);
            attachment.setContentType(contentType);
            attachment.setOriginalName(originalName);

            attachmentRepository.save(attachment);

            return defaultMapper.toDTO(attachment);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}

