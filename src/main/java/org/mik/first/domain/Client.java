package org.mik.first.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true, exclude = {"country", "jobs"})
@ToString(callSuper = true)
@Getter
@Setter

@Entity
@Table(name = Client.TBL_NAME)
@SequenceGenerator(name = "id_generator", sequenceName = "client_seq", allocationSize = 1)
public class Client extends AbstractDomain<Long> {

    public static final String TBL_NAME = "client";
    public static final String FLD_NAME = "name";
    public static final String FLD_ADDRESS = "address";
    public static final String FLD_COUNTRY = "country";
    public static final String FLD_AMOUNT = "amount";

    @NotNull
    @Length(min = 3, max = 100)
    @Column(name = FLD_NAME, nullable = false, length = 100)
    private String name;

    @NotNull
    @Length(min = 3, max = 30)
    @Column(name = FLD_ADDRESS, nullable = false, length = 30)
    private String address;

    @NotNull
    @ManyToOne(targetEntity = Country.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = FLD_COUNTRY, nullable = false)
    @ToString.Exclude
    private Country country;

    @NotNull
    @Column(name = FLD_AMOUNT, nullable = false)
    private Integer amount;

    @ToString.Exclude
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, orphanRemoval = true,
               cascade = CascadeType.MERGE, targetEntity = Job.class)
    private Set<Job> jobs;

}
