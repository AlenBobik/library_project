package com.example.library_project.mappers;

import com.example.library_project.dto.KnjigaIzvodDto;
import com.example.library_project.entities.KnjigaIzvod;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface KnjigaIzvodMapper {

    KnjigaIzvodDto convertToKnjigaIzvodDto(KnjigaIzvod knjigaIzvod);

    @Mapping(target = "avtor", ignore = true)
    KnjigaIzvod mapToKnjigaIzvod(KnjigaIzvodDto knjigaIzvodDto);

    @Mapping(target = "avtor", ignore = true)
    void updateValuesOfExistingKnjigaIzvod(
            KnjigaIzvodDto knjigaIzvodDto, @MappingTarget KnjigaIzvod knjigaIzvod);

    List<KnjigaIzvodDto> mapKnjigaIzvodToDtoList(List<KnjigaIzvod> knjigaIzvod);
}
