package org.mik.first.testdata;

import org.mik.first.AbstractTest;
import org.mik.first.domain.Job;
import org.mik.first.domain.JobType;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public interface JobData extends Data {
    public static List<Job> TEST_DATA = new LinkedList<>(
            List.of(
                    Job.builder()
                            .name("test name1")
                            .client(ClientData.TEST_DATA.get(0))
                            .value(42)
                            .jobType(JobType.SELL)
                            .starting(LocalDateTime.now())
                            .build(),
                    Job.builder()
                            .name("test name2")
                            .client(ClientData.TEST_DATA.get(1))
                            .value(43)
                            .jobType(JobType.BUY)
                            .starting(LocalDateTime.now())
                            .build()
            )
    );

    public static List<AbstractTest.InvalidEntry<Long, Job, Exception>> INVALID_DATA = List.of(
            new AbstractTest.InvalidEntry<>(
                    List.of(
                            Job.builder()
//                         .name("test_name")
                                    .client(ClientData.TEST_DATA.get(1))
                                    .value(43)
                                    .jobType(JobType.BUY)
                                    .starting(LocalDateTime.now())
                                    .build()
                    ),
                    Exception.class
            ),
            new AbstractTest.InvalidEntry<>(
                    List.of(
                            Job.builder()
                                    .name("test_name")
                                    //                              .client(ClientData.TEST_DATA.get(1))
                                    .value(43)
                                    .jobType(JobType.BUY)
                                    .starting(LocalDateTime.now())
                                    .build()
                    ),
                    Exception.class
            ),
            new AbstractTest.InvalidEntry<>(
                    List.of(
                            Job.builder()
                                    .name("test_name")
                                    .client(ClientData.TEST_DATA.get(1))
                                    .value(43)
                                    //.jobType(JobType.BUY)
                                    .starting(LocalDateTime.now())
                                    .build()
                    ),
                    Exception.class
            )
    );

}
