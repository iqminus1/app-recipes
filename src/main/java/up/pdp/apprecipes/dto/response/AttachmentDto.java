package up.pdp.apprecipes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttachmentDto implements Serializable {
    private UUID id;
    private boolean deleted;
    private String name;
    private String originalName;
    private String path;
    private String contentType;
    private Long size;
}