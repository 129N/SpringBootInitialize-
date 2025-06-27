package org.mik.first.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Table(name=Person.TBL_NAME)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper=true)
public class Person extends AbstractDomain<Long> {
    public static final String TBL_NAME="person";
    public static final String FLD_CLIENT="client_id";
    public static final String FLD_PERSONAL_ID="personal_id";
    public static final String FLD_BIRTH_DATE="birth_date";

    @NotNull
    @ManyToOne(targetEntity=Client.class, fetch= FetchType.EAGER)
    @JoinColumn(name=FLD_CLIENT, nullable=false)
    private Client client;

    @NotNull
    @Length(min=12, max=12)
    @Column(name=FLD_PERSONAL_ID, nullable=false, unique=true, length = 12)
    private String personalId;

    @NotNull
    @Column(name=FLD_BIRTH_DATE, nullable=false)
    private LocalDate birthDate;

}

