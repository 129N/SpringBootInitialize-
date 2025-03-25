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

    public static final String FLD_ID = "id";
    public static final String FLD_NAME = "name";

    @Id
    @Column(name = FLD_ID, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @EqualsAndHashCode.Include
    private ID id;

    @Version
    @EqualsAndHashCode.Include
    private Integer version;

    @CreationTimestamp
    @EqualsAndHashCode.Exclude
    private LocalDateTime created;

    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    private LocalDateTime updated;

}
