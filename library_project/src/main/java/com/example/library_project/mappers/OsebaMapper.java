package com.example.library_project.mappers;

import com.example.library_project.dto.OsebaDto;
import com.example.library_project.entities.Oseba;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OsebaMapper {

    @Mapping(target = "izkaznicaOznaka", source = "izkaznica.izkaznicaOznaka")
    OsebaDto convertToOsebaDto(Oseba oseba);

    @Mapping(target = "izkaznica", ignore = true)
    // @Mapping(target = "user", ignore = true)
    Oseba mapDtoToOseba(OsebaDto osebaDto);

    @Mapping(target = "izkaznica", ignore = true)
    @Mapping(target = "osebaSpol", ignore = true)
    @Mapping(target = "osebaStatus", ignore = true)
    // @Mapping(target = "user", ignore = true)
    void updateValuesOdExistingOseba(OsebaDto osebaDto, @MappingTarget Oseba oseba);

    List<OsebaDto> mapOsebaToDtoList(List<Oseba> osebe);
}
