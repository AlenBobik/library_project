package com.example.library_project.mappers;

import com.example.library_project.dto.IzposojaDto;
import com.example.library_project.entities.Izposoja;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IzposojaMapper {

    @Mapping(target = "knjigaIzvodIsbn", source = "knjigaIzvod.knjigaIzvodIsbn")
    @Mapping(target = "userUsername", source = "user.username")
    IzposojaDto convertToIzposojaDto(Izposoja izposoja);

    @Mapping(target = "knjigaIzvod", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "izposojaId", ignore = true)
    Izposoja mapToIzposoja(IzposojaDto izposojaDto);

    @Mapping(target = "knjigaIzvod", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "izposojaId", ignore = true)
    void updateValuesOfExistingIzposoja(IzposojaDto izposojaDto, @MappingTarget Izposoja izposoja);

    List<IzposojaDto> mapIzposojaToDtoList(List<Izposoja> izposoja);
}
