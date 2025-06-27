package org.mik.first.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.management.relation.Role;
import java.util.List;

@Entity
@Table(name = RoleDomain.TBL_NAME)


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RoleDomain extends AbstractDomain<Long>{
    public static final String TBL_NAME ="role domain";


    @NotNull
    @Length(max = 30)
    @Column(name = "name", nullable = false, unique = true, length = 30)
    private String name;

    @NotNull
    @Length(max = 255)
    @Column(name = "passowrd", nullable = false, length = 255)
    String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleDomain> roles;

}
