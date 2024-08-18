package up.pdp.apprecipes.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.pdp.apprecipes.dto.StepCrudDto;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.Step;
import up.pdp.apprecipes.repository.StepRepository;
import up.pdp.apprecipes.service.StepService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StepServiceImpl implements StepService {
    private final StepRepository stepRepository;
    @Override
    public Step save(StepCrudDto dto) {
        return Step.builder()
                .step(dto.getStep())
                .description(dto.getDescription())
                .build();
    }

    @Override
    public StepCrudDto getById(UUID id) {
        Step step = stepRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Step"));
        return new StepCrudDto(step);
    }

    @Override
    public List<Step> getAll() {
        return stepRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        stepRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Step"));
        stepRepository.deleteById(id);
    }
}
