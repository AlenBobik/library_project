package com.example.library_project.mappers;

import com.example.library_project.dto.IzkaznicaDto;
import com.example.library_project.entities.Izkaznica;
import com.example.library_project.enums.Status;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-05T13:57:03+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class IzkaznicaMapperImpl implements IzkaznicaMapper {

    @Override
    public IzkaznicaDto convertToizkaznicaDto(Izkaznica izkaznica) {
        if ( izkaznica == null ) {
            return null;
        }

        IzkaznicaDto izkaznicaDto = new IzkaznicaDto();

        izkaznicaDto.setIzkaznicaOznaka( izkaznica.getIzkaznicaOznaka() );
        izkaznicaDto.setIzkaznicaDatumIzdaje( izkaznica.getIzkaznicaDatumIzdaje() );
        izkaznicaDto.setIzkaznicaDatumpoteka( izkaznica.getIzkaznicaDatumpoteka() );
        if ( izkaznica.getIzkaznicaStatus() != null ) {
            izkaznicaDto.setIzkaznicaStatus( izkaznica.getIzkaznicaStatus().name() );
        }

        return izkaznicaDto;
    }

    @Override
    public Izkaznica mapDtoToIzkaznica(IzkaznicaDto izkaznicaDto) {
        if ( izkaznicaDto == null ) {
            return null;
        }

        Izkaznica izkaznica = new Izkaznica();

        izkaznica.setIzkaznicaOznaka( izkaznicaDto.getIzkaznicaOznaka() );
        izkaznica.setIzkaznicaDatumIzdaje( izkaznicaDto.getIzkaznicaDatumIzdaje() );
        izkaznica.setIzkaznicaDatumpoteka( izkaznicaDto.getIzkaznicaDatumpoteka() );
        if ( izkaznicaDto.getIzkaznicaStatus() != null ) {
            izkaznica.setIzkaznicaStatus( Enum.valueOf( Status.class, izkaznicaDto.getIzkaznicaStatus() ) );
        }

        return izkaznica;
    }

    @Override
    public void updateValuesOfExistingIzkaznica(IzkaznicaDto izkaznicaDto, Izkaznica izkaznica) {
        if ( izkaznicaDto == null ) {
            return;
        }

        izkaznica.setIzkaznicaDatumIzdaje( izkaznicaDto.getIzkaznicaDatumIzdaje() );
        izkaznica.setIzkaznicaDatumpoteka( izkaznicaDto.getIzkaznicaDatumpoteka() );
    }

    @Override
    public List<IzkaznicaDto> mapIzkaznicaToDtoList(List<Izkaznica> izkaznica) {
        if ( izkaznica == null ) {
            return null;
        }

        List<IzkaznicaDto> list = new ArrayList<IzkaznicaDto>( izkaznica.size() );
        for ( Izkaznica izkaznica1 : izkaznica ) {
            list.add( convertToizkaznicaDto( izkaznica1 ) );
        }

        return list;
    }
}
