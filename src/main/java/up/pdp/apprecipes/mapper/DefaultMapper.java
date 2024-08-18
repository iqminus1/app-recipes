package up.pdp.apprecipes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import up.pdp.apprecipes.dto.AttachmentDto;
import up.pdp.apprecipes.dto.IngredientCRUDDto;
import up.pdp.apprecipes.model.Attachment;
import up.pdp.apprecipes.model.Ingredient;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DefaultMapper {
    AttachmentDto toDTO(Attachment attachment);
    void updateEntity(IngredientCRUDDto dto,@MappingTarget Ingredient ingredient);
}