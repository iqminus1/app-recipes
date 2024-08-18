package up.pdp.apprecipes.model;

import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import jakarta.persistence.Table;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import up.pdp.apprecipes.model.templates.AbsUUIDEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "users")
@SQLRestriction("deleted = false")
@SQLDelete(sql = "update users set deleted = true where id = ?")
public class User extends AbsUUIDEntity implements UserDetails, Serializable {

    private String email;

    private String password;

    private String name;

    private boolean enable;

    private boolean admin;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = "User";
        if (admin) role = "Admin";
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
