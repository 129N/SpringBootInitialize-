package org.mik.first.service;




import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.mik.first.domain.Country;
import org.mik.first.dto.CountryDTO;
import org.mik.first.dto.mapper.CountryMapper;
import org.mik.first.dto.mapper.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@Transactional
public class CountryService extends AbstractService<Long, Country, CountryDTO> {



    @Autowired
    public CountryService(JpaRepository<Country, Long> repository,
                          CountryMapper mapper) {

        super(repository, mapper);
    }

    @Override
    protected Country copy(Country original, Country modified) {
        return null;
    }

}
