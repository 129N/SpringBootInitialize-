package org.mik.first.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Table(name=Company.TBL_NAME)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper=true, exclude={"client"})
public class Company extends AbstractDomain<Long> {
    public static final String TBL_NAME="company";

    public static final String FLD_CLIENT="client_id";
    public static final String FLD_ESTABLISHED="established";
    public static final String FLD_CAPITALISATION = "capitalisation";
    public static final String FLD_TAX_ID = "tax_id";

    @NotNull
    @OneToOne(targetEntity=Client.class, fetch= FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name=FLD_CLIENT, nullable=false)
    private Client client;

    @Column(name=FLD_ESTABLISHED, nullable=false)
    private Integer established;

    @Column(name=FLD_CAPITALISATION)
    @Builder.Default
    private Long capitalisation=0L;

    @NotNull
    @Length(min = 9, max = 9)
    @Column(name = FLD_TAX_ID, nullable = false, unique = true, length = 9)
    private String taxId;

}

