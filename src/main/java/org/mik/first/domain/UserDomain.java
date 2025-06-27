package org.mik.first.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name =UserDomain.TBL_NAME)

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)

@NamedNativeQuery(name = "userDomain.addrole", query = """
insert into users_role (user_id, role_id)
values (:useId, :roleid)
""")
@NamedNativeQuery(name ="userDomain.revokeRole", query = """
delete from user_roles where user_id=:userId and role_id= :roleId
""")
public class UserDomain extends AbstractDomain<Long> implements UserDetails {

    public static final String TBL_NAME = "user domain";

    @NotNull
    @Length(min = 1, max = 30)
    @Column(name = "name", nullable = false, unique = true, length = 30)
    String name;

    @NotNull
    @Length(max = 255)
    @Column(name = "passowrd", nullable = false, length = 255)
    String password;



    @Column (name = "enabled", nullable = false)
    private boolean enabled;

    @Column (name = "locked", nullable = false)
    private boolean locked;

    @Column(name = "expired")
    private LocalDate expired;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleDomain> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return roles.stream().map(
                roleDoamin -> new SimpleGrantedAuthority(roleDoamin.getName())).toList();

    }

    @Override
    public String getUsername() {
        return this.name;
    }
//
//    @Override
//    public boolean isAccountNonlocked(){
//        return !(this.enabled && !this.locked);
//    }


    @Override
    public boolean isAccountNonLocked() {
        return !(this.enabled && !this.locked);
    }

//    @Override
//    public boolean isCredentialNonexpired(){
//        return !(this.enabled && !this.locked);
//    }


    @Override
    public boolean isCredentialsNonExpired() {
        return !(this.enabled && !this.locked);
    }

//    @Override
//    public boolean isAccountNonExpired(){
//        retrun enabled && !locked && ((expired==null) || expired.isAfter(LocalDate.now()));
//    }


    @Override
    public boolean isAccountNonExpired() {
        return enabled && !locked && ((expired==null) || expired.isAfter(LocalDate.now()));
    }
}
