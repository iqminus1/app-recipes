package up.pdp.apprecipes.service;

import up.pdp.apprecipes.dto.StepCrudDto;
import up.pdp.apprecipes.model.Step;

import java.util.List;
import java.util.UUID;

public interface StepService {
    Step save(StepCrudDto step);
    StepCrudDto getById(UUID id);
    List<Step> getAll();
    void deleteById(UUID id);
}
