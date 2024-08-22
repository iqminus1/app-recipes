package up.pdp.apprecipes.model.templates;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.UUID;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbsAuditEntity extends AbsDateEntity implements Serializable {
    @CreatedBy
    @Column(updatable = false)
    private UUID createBy;

    @LastModifiedBy
    private UUID updateBy;
}
