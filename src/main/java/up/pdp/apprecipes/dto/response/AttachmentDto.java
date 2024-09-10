package up.pdp.apprecipes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import up.pdp.apprecipes.model.Attachment;

import java.io.Serializable;
import java.util.UUID;


@AllArgsConstructor
@Getter
public class AttachmentDto implements Serializable {
    private UUID id;
    private String originalName;
    private String name;
    private String type;
    private Long size;
    private String path;

    public AttachmentDto(Attachment attachment) {
        this.id = attachment.getId();
        this.originalName = attachment.getOriginalName();
        this.name = attachment.getName();
        this.type = attachment.getType();
        this.size = attachment.getSize();
        this.path = attachment.getPath();
    }
}