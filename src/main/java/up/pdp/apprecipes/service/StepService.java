package up.pdp.apprecipes.service;

import up.pdp.apprecipes.dto.StepDto;

import java.util.List;
import java.util.UUID;

public interface StepService {
    StepDto save(StepDto stepDto);
    StepDto getById(UUID id);
    List<StepDto> getAll();
    void deleteById(UUID id);
}
