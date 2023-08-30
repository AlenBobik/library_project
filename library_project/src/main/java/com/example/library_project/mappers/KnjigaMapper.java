package com.example.library_project.mappers;

import com.example.library_project.dto.KnjigaDto;
import com.example.library_project.entities.Knjiga;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface KnjigaMapper {

    @Mapping(target = "knjigaIzvodIsbn", source = "knjigaIzvod.knjigaIzvodIsbn")
    @Mapping(target = "knjiznicaId", source = "knjiznica.knjiznicaId")
    KnjigaDto convertToKnjigaDto(Knjiga knjiga);

    @Mapping(target = "knjigaIzvod", ignore = true)
    @Mapping(target = "knjiznica", ignore = true)
    Knjiga mapDtoToKnjiga(KnjigaDto knjigaDto);

    @Mapping(target = "knjigaIzvod", ignore = true)
    @Mapping(target = "knjiznica", ignore = true)
    @Mapping(target = "knjigaUuid", ignore = true)
    @Mapping(target = "knjigaStatus", ignore = true)
    void updateValuesOfExistingKnjiga(KnjigaDto knjigaDto, @MappingTarget Knjiga knjiga);

    List<KnjigaDto> mapKnjigaToDtoList(List<Knjiga> knjige);
}
