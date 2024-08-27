package up.pdp.apprecipes.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import up.pdp.apprecipes.dto.response.ApiResultDto;

import java.util.UUID;

public interface AttachmentService {
    void read(HttpServletResponse resp, UUID id);
    ApiResultDto<?> create(HttpServletRequest req);
    void delete(UUID id);
}
