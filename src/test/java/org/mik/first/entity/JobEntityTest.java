package org.mik.first.entity;

import org.mik.first.domain.Job;
import org.mik.first.testdata.JobData;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class JobEntityTest extends AbstractEntityTest<Long, Job, Exception> implements JobData {


    public JobEntityTest(JpaRepository<Job, Long> repository) {
        super(repository);
    }

    @Override
    protected List<InvalidEntry<Long, Job, Exception>> getInvalidEntities() {
        return JobEntityTest.INVALID_DATA;
    }

    @Override
    protected Class<Job> getClazz() {
        return Job.class;
    }

    @Override
    protected List<ValidEntity<Long, Job>> getValidEntities() {
        return TEST_DATA.stream().map(j -> new ValidEntity<>(j, e -> {
            e.setName(e.getName() + "+m");
            e.setValue(e.getValue() + 1);
        })).toList();
    }

    @Override
    protected List<Job> getTestData() {
        return TEST_DATA;
    }
}
