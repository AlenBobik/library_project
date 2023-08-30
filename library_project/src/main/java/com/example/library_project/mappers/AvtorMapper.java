package com.example.library_project.mappers;

import com.example.library_project.dto.AvtorDto;
import com.example.library_project.entities.Avtor;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AvtorMapper {

    AvtorDto convertToAvtorDto(Avtor avtor);

    @Mapping(target = "knjigaIzvod", ignore = true)
    Avtor mapDtoToAvtor(AvtorDto avtorDto);

    @Mapping(target = "knjigaIzvod", ignore = true)
    @Mapping(target = "avtorId", ignore = true)
    void updateValuesOfExistingAvtor(AvtorDto avtorDto, @MappingTarget Avtor avtor);

    List<AvtorDto> mapAvtorToDtoList(List<Avtor> avtor);
}
