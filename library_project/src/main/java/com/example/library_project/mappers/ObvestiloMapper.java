package com.example.library_project.mappers;

import com.example.library_project.dto.ObvestiloDto;
import com.example.library_project.entities.Obvestilo;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ObvestiloMapper {

    ObvestiloDto convertToObvestiloDto(Obvestilo obvestilo);

    // @Mapping(target = "user", ignore = true)
    Obvestilo mapDtoToObvestilo(ObvestiloDto obvestiloDto);

    // @Mapping(target = "user", ignore = true)
    void updateValuesOfExistingObvestilo(
            ObvestiloDto obvestiloDto, @MappingTarget Obvestilo obvestilo);

    List<ObvestiloDto> mapObvestiloToDtoList(List<Obvestilo> obvestila);
}
