package up.pdp.apprecipes.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import up.pdp.apprecipes.dto.ApiResultDTO;

public interface AttachmentService {
    void read(HttpServletResponse resp,Integer id);
    ApiResultDTO<?> create(HttpServletRequest req);
    ApiResultDTO<?> update(HttpServletRequest req, Integer id);
    void delete(Integer id);
}
