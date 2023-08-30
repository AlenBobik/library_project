package com.example.library_project.mappers;

import com.example.library_project.dto.ZaposlenDto;
import com.example.library_project.entities.Zaposlen;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ZaposlenMapper {

    ZaposlenDto convertToZaposlenDto(Zaposlen zaposlen);

    // @Mapping(target = "user", ignore = true)
    Zaposlen mapDtoToZaposlen(ZaposlenDto zaposlenDto);

    // @Mapping(target = "user", ignore = true)
    @Mapping(target = "zaposlenOznakaPogodbe", ignore = true)
    void updateValuesOfExistingZaposlen(ZaposlenDto zaposlenDto, @MappingTarget Zaposlen zaposlen);

    List<ZaposlenDto> mapZaposlenToDtoList(List<Zaposlen> zaposleni);
}
