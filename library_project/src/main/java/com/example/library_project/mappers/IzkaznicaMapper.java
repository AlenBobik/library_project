package com.example.library_project.mappers;

import com.example.library_project.dto.IzkaznicaDto;
import com.example.library_project.entities.Izkaznica;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IzkaznicaMapper {

    IzkaznicaDto convertToizkaznicaDto(Izkaznica izkaznica);

    @Mapping(target = "oseba", ignore = true)
    Izkaznica mapDtoToIzkaznica(IzkaznicaDto izkaznicaDto);

    @Mapping(target = "oseba", ignore = true)
    @Mapping(target = "izkaznicaStatus", ignore = true)
    @Mapping(target = "izkaznicaOznaka", ignore = true)
    void updateValuesOfExistingIzkaznica(
            IzkaznicaDto izkaznicaDto, @MappingTarget Izkaznica izkaznica);

    List<IzkaznicaDto> mapIzkaznicaToDtoList(List<Izkaznica> izkaznica);
}
