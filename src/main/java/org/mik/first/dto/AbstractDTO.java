package org.mik.first.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode

public class AbstractDTO <ID extends Serializable> implements Serializable{

    private ID id;
    private Integer version;
    private LocalDateTime created;
    private LocalDateTime updated;
}
