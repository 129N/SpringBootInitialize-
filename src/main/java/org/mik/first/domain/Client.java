package org.mik.first.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true, exclude = {"country", "job"})
@ToString(callSuper = true)
@Getter
@Setter

@Entity
@Table(name = Client.TBL_NAME)
@SequenceGenerator(name = "SEQ", sequenceName = "client_seq", allocationSize = 1)
public abstract class Client extends AbstractDomain<Long> {
    public static final String TBL_NAME = "client";


    @Length(min = 2, max = 100)
    @NotBlank
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Length(min = 2, max = 100)
    @NotBlank
    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @ManyToOne(targetEntity = Country.class)
    private Country country;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Builder.Default

    @OneToMany(targetEntity = Job.class, mappedBy = "client")
    private List<Job> jobs=new LinkedList<>();


//    public synchronized void addJob(@NonNull Job job, @NonNull Client partner, @NonNull JobType type) throws BadParameterException {
//        job.setClient(partner);
//        if (!job.isValid())
//            throw new BadParameterException("job is invalid: %s".formatted(job));
//        job.setJobType(type);
//        jobs.add(job);
//    }

}
