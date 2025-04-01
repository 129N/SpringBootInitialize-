package org.mik.first.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import org.hibernate.validator.constraints.Length;


import java.util.LinkedList;
import java.util.List;
import jakarta.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true, exclude = {"country", "jobs"})
@ToString(callSuper = true, exclude = {"country", "jobs"})


@Entity
@Table(name = Client.TBL_NAME)
@SequenceGenerator(name ="SEQ", sequenceName = "country_seq", allocationSize = 1)
public class Client extends AbstractDomain<Long> {

    public static final String TBL_NAME = "client";


    @Length(min=2, max =100)
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



    @OneToMany(targetEntity = Job.class, mappedBy = "client")
    private List<Job> jobs = new LinkedList<>();
    /*
    @Length(min = 2, max = 100)
    @NotBlank
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "country", nullable = false)
    private Country country;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @OneToMany (targetEntity = Job.class, mappedBy = "client")
    @Builder.Default
    private List<Job> jobs=new LinkedList<>();

     */






    /*
    public synchronized void addJob(@NonNull Job job, @NonNull Client partner, @NonNull JobType type) throws BadParameterException {
        job.setClient(partner);
        if (!job.isValid())
            throw new BadParameterException("job is invalid: %s".formatted(job));
        job.setJobType(type);
        jobs.add(job);
    }*/

}
