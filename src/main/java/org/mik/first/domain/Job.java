package org.mik.first.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;


import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = {"client"} )
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = Job.TBL_NAME)
@SequenceGenerator(name ="SEQ", sequenceName = "country_seq", allocationSize = 1)
public class Job extends AbstractDomain<Long> {

    public static final String TBL_NAME = "job";

    @Builder.Default
    private LocalDateTime created=LocalDateTime.now();

    @Column(name ="starting")
    private LocalDateTime starting;

    @Column(name ="finished")
    private LocalDateTime finished;

    /*
    @ToString.Exclude
    @OneToMany(targetEntity = Client.class, mappedBy = "jobs")
    private Client client;

     */
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name ="client", nullable = false)
    private Client client;


    @NotBlank
    @Length(min=2, max=100)
    @Column(name ="jobname",nullable = false,length = 100)
    private String jobName;

    @Column(name ="value", nullable = false)
    private int value;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    public boolean isValid() {
        return created != null && client!=null &&
                jobName != null && !jobName.isBlank();
    }

    @ToString.Include
    private String getClientName() {
        return this.client==null ? "Unknown" : client.getName();
    }
}
