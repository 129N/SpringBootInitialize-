package org.mik.first.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.mik.first.domain.JobType;

import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class jobDTO extends AbstractDTO<Long>{

    private LocalDateTime starting ;
    private LocalDateTime finished;
    private Long clientId;
    private String name;
    private int value;
    private JobType jobType;

}
