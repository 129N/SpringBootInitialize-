package org.mik.first.dto.mapper;

import org.mik.first.domain.Country;
import org.mik.first.dto.CountryDTO;

public interface CountryMapper extends EntityMapper<Long, Country, CountryDTO>{

    Country toEntity(CountryDTO dto);

}
