package up.pdp.apprecipes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import up.pdp.apprecipes.dto.IngredientDto;
import up.pdp.apprecipes.dto.response.AttachmentDto;
import up.pdp.apprecipes.model.Attachment;
import up.pdp.apprecipes.model.Ingredient;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DefaultMapper {
    AttachmentDto toDTO(Attachment attachment);
    void ingredientUpdateEntity(IngredientDto dto, @MappingTarget Ingredient ingredient);
    void ingredientEntityToDto(Ingredient ingredient,@MappingTarget IngredientDto dto);
    void ingredientsEntityToDto(List<Ingredient> ingredients, @MappingTarget List<IngredientDto> ingredientDto);
    IngredientDto map(Ingredient value);
}