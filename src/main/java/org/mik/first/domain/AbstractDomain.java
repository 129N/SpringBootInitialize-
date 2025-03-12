package org.mik.first.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@SuperBuilder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@MappedSuperclass
public abstract class AbstractDomain<ID extends Serializable> implements Serializable {

    public static final String FLD_ID="id";
    public static final String FLD_CREATED="created";
    public static final String FLD_UPDATED="updated";

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @Column(name = FLD_ID, nullable = false, unique = true)
    private ID id;

    @EqualsAndHashCode.Exclude
    @CreationTimestamp
    private LocalDateTime created;

    @EqualsAndHashCode.Exclude
    @UpdateTimestamp
    private LocalDateTime updated;

    @EqualsAndHashCode.Include
    @Version
    private Long version;

}
