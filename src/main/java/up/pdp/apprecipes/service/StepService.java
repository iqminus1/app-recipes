package up.pdp.apprecipes.service;

import up.pdp.apprecipes.dto.StepDto;
import up.pdp.apprecipes.model.Step;

import java.util.List;
import java.util.UUID;

public interface StepService {
    Step save(StepDto step);
    StepDto getById(UUID id);
    List<Step> getAll();
    void deleteById(UUID id);
}
