package org.mik.first.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter

@Entity
@Table(name = Country.TBL_NAME)
@SequenceGenerator(name = "SEQ", sequenceName = "country_seq", allocationSize = 1)
public class Country extends AbstractDomain<Long> {
    public static final String TBL_NAME = "country";
    public static final String FLD_SIGN = "sign";

    public static final String COUNTRY_NOT_FOUND = "Country not found";

    @NotNull
    @Length(min = 1, max = 30)
    @Column(name = FLD_NAME, nullable = false, unique = true)
    private String name;

    @NotNull
    @Length(min = 1, max = 5)
    @Column(name = FLD_SIGN, nullable = false, unique = true)
    private String sign;

}
