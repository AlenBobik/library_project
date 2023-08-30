package com.example.library_project.mappers;

import com.example.library_project.dto.RezervacijaDto;
import com.example.library_project.entities.Rezervacija;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RezervacijaMapper {

    @Mapping(target = "knjigaIzvodIsbn", source = "knjigaIzvod.knjigaIzvodIsbn")
    @Mapping(target = "userUsername", source = "user.username")
    RezervacijaDto convertToRezervacijaDto(Rezervacija rezervacija);

    @Mapping(target = "knjigaIzvod", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "rezervacijaId", ignore = true)
    Rezervacija mapToRezervacija(RezervacijaDto rezervacijaDto);

    @Mapping(target = "knjigaIzvod", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "rezervacijaId", ignore = true)
    void updateValuesOfExistingRezervacija(
            RezervacijaDto rezervacijaDto, @MappingTarget Rezervacija rezervacija);

    List<RezervacijaDto> mapRezervacijaToDtoList(List<Rezervacija> rezervacija);
}
