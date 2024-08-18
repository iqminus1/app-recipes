package up.pdp.apprecipes.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import up.pdp.apprecipes.dto.ApiResultDto;

public interface AttachmentService {
    void read(HttpServletResponse resp,Integer id);
    ApiResultDto<?> create(HttpServletRequest req);
    ApiResultDto<?> update(HttpServletRequest req, Integer id);
    void delete(Integer id);
}
