package up.pdp.apprecipes.service;

import up.pdp.apprecipes.dto.request.StepCRUDDto;
import up.pdp.apprecipes.dto.response.StepDto;

import java.util.List;
import java.util.UUID;

public interface StepService {
    StepDto save(StepCRUDDto stepDto);
    StepDto getById(UUID id);
    List<StepDto> getAll();
    void deleteById(UUID id);
}
