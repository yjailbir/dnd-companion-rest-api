package program.service.dnd.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import program.service.dnd.data.entity.User;

import java.util.Collection;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    //private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    //@JsonIgnore
    private String password;

    public static UserDetailsImpl build(User user) {

        return new UserDetailsImpl(user.getId(),
                user.getUsername(),
                user.getPassword());
    }


  /*  @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }*/

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
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public Collection<? extends GrantedAuthority>
    getAuthorities() {

        return null;
    }
}
