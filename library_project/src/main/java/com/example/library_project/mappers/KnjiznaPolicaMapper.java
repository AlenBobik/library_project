package com.example.library_project.mappers;

import com.example.library_project.dto.KnjiznaPolicaDto;
import com.example.library_project.entities.KnjiznaPolica;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface KnjiznaPolicaMapper {

    KnjiznaPolicaDto convertToKnjiznaPolicaDto(KnjiznaPolica knjiznaPolica);

    @Mapping(target = "knjigaIzvod", ignore = true)
    KnjiznaPolica mapToKnjiznaPolica(KnjiznaPolicaDto knjiznaPolicaDto);

    @Mapping(target = "knjigaIzvod", ignore = true)
    @Mapping(target = "knjiznaPolicaId", ignore = true)
    void updateValuesOfExistingKnjiznaPolica(
            KnjiznaPolicaDto knjiznaPolicaDto, @MappingTarget KnjiznaPolica knjiznaPolica);

    List<KnjiznaPolicaDto> mapKnjiznaPolicaToDtoList(List<KnjiznaPolica> knjiznaPolica);
}
