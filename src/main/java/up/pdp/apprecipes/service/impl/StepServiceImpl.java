package up.pdp.apprecipes.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.pdp.apprecipes.dto.request.StepCRUDDto;
import up.pdp.apprecipes.dto.response.StepDto;
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
    public StepDto save(StepCRUDDto dto) {
        Step step = Step.builder()
                .step(dto.getStep())
                .description(dto.getDescription())
                .build();

        return new StepDto(stepRepository.save(step));
    }

    @Override
    public StepDto getById(UUID id) {
        return new StepDto(stepRepository.getById(id));
    }

    @Override
    public List<StepDto> getAll() {
        return stepRepository.findAll()
                .stream()
                .map(StepDto::new)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        stepRepository.delete(stepRepository.getById(id));
    }
}
