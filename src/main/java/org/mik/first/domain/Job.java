package org.mik.first.domain;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"client"})
@ToString(callSuper = true)
@SuperBuilder
@Getter
@Setter

@Entity
@Table(name = Job.TBL_NAME)
public class Job extends AbstractDomain<Long> {
    public static final String TBL_NAME="jobs";
    public static final String FLD_STARTING = "starting";
    public static final String FLD_FINISHED = "finished";
    public static final String FLD_CLIENT = "client";
    public static final String FLD_VALUE = "val";
    public static final String FLD_JOB_TYPE = "job_type";

    @Column(name = FLD_STARTING, nullable = true)
    private LocalDateTime starting;

    @Column(name = FLD_FINISHED, nullable = true)
    private LocalDateTime finished;

    @ManyToOne(targetEntity = Client.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = FLD_CLIENT, nullable = false)
    @Fetch(FetchMode.JOIN)
    private Client client;

    @NotNull
    @Column(name = FLD_NAME, nullable = false)
    private String name;

    @NotNull
    @Column(name = FLD_VALUE, nullable = false)
    private int value;

    @Enumerated(EnumType.STRING)
    @Column(name = FLD_JOB_TYPE, nullable = false)
    private JobType jobType;

    @PrePersist
    public void prePersist() {
        if (starting!=null)
            this.starting=this.starting.truncatedTo(ChronoUnit.MILLIS);
    }

    public boolean isValid() {
        return client!=null &&
                name != null && !name.isBlank();
    }


}
