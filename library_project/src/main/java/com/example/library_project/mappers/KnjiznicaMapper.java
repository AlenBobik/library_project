package com.example.library_project.mappers;

import com.example.library_project.dto.KnjiznicaDto;
import com.example.library_project.entities.Knjiznica;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface KnjiznicaMapper {

    KnjiznicaDto convertToKnjiznicaDto(Knjiznica knjiznica);

    @Mapping(target = "knjiga", ignore = true)
    Knjiznica mapDtoToKnjiznica(KnjiznicaDto knjiznicaDto);

    @Mapping(target = "knjiga", ignore = true)
    @Mapping(target = "knjiznicaStatus", ignore = true)
    @Mapping(target = "knjiznicaId", ignore = true)
    void updateValuesOfExistingknjiznica(
            KnjiznicaDto knjiznicaDto, @MappingTarget Knjiznica knjiznica);

    List<KnjiznicaDto> mapKnjiznicaToDtoList(List<Knjiznica> knjiznica);
}
