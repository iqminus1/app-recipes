package up.pdp.apprecipes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import up.pdp.apprecipes.dto.AttachmentDTO;
import up.pdp.apprecipes.model.Attachment;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DefaultMapper {
    AttachmentDTO toDTO(Attachment attachment);
}