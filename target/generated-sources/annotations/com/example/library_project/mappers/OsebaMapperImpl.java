package com.example.library_project.mappers;

import com.example.library_project.dto.OsebaDto;
import com.example.library_project.entities.Izkaznica;
import com.example.library_project.entities.Oseba;
import com.example.library_project.enums.OsebaSpol;
import com.example.library_project.enums.Status;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class OsebaMapperImpl implements OsebaMapper {

    @Override
    public OsebaDto convertToOsebaDto(Oseba oseba) {
        if ( oseba == null ) {
            return null;
        }

        OsebaDto osebaDto = new OsebaDto();

        osebaDto.setIzkaznicaOznaka( osebaIzkaznicaIzkaznicaOznaka( oseba ) );
        osebaDto.setOsebaIme( oseba.getOsebaIme() );
        osebaDto.setOsebaPriimek( oseba.getOsebaPriimek() );
        if ( oseba.getOsebaDatumRojstva() != null ) {
            osebaDto.setOsebaDatumRojstva( LocalDate.parse( oseba.getOsebaDatumRojstva() ) );
        }
        if ( oseba.getOsebaSpol() != null ) {
            osebaDto.setOsebaSpol( oseba.getOsebaSpol().name() );
        }
        osebaDto.setOsebaEmso( oseba.getOsebaEmso() );
        osebaDto.setOsebaEmail( oseba.getOsebaEmail() );
        osebaDto.setOsebaTelefon( oseba.getOsebaTelefon() );
        if ( oseba.getOsebaStatus() != null ) {
            osebaDto.setOsebaStatus( oseba.getOsebaStatus().name() );
        }

        return osebaDto;
    }

    @Override
    public Oseba mapDtoToOseba(OsebaDto osebaDto) {
        if ( osebaDto == null ) {
            return null;
        }

        Oseba oseba = new Oseba();

        oseba.setOsebaIme( osebaDto.getOsebaIme() );
        oseba.setOsebaPriimek( osebaDto.getOsebaPriimek() );
        if ( osebaDto.getOsebaDatumRojstva() != null ) {
            oseba.setOsebaDatumRojstva( DateTimeFormatter.ISO_LOCAL_DATE.format( osebaDto.getOsebaDatumRojstva() ) );
        }
        if ( osebaDto.getOsebaSpol() != null ) {
            oseba.setOsebaSpol( Enum.valueOf( OsebaSpol.class, osebaDto.getOsebaSpol() ) );
        }
        oseba.setOsebaEmso( osebaDto.getOsebaEmso() );
        oseba.setOsebaEmail( osebaDto.getOsebaEmail() );
        oseba.setOsebaTelefon( osebaDto.getOsebaTelefon() );
        if ( osebaDto.getOsebaStatus() != null ) {
            oseba.setOsebaStatus( Enum.valueOf( Status.class, osebaDto.getOsebaStatus() ) );
        }

        return oseba;
    }

    @Override
    public void updateValuesOdExistingOseba(OsebaDto osebaDto, Oseba oseba) {
        if ( osebaDto == null ) {
            return;
        }

        oseba.setOsebaIme( osebaDto.getOsebaIme() );
        oseba.setOsebaPriimek( osebaDto.getOsebaPriimek() );
        if ( osebaDto.getOsebaDatumRojstva() != null ) {
            oseba.setOsebaDatumRojstva( DateTimeFormatter.ISO_LOCAL_DATE.format( osebaDto.getOsebaDatumRojstva() ) );
        }
        else {
            oseba.setOsebaDatumRojstva( null );
        }
        oseba.setOsebaEmso( osebaDto.getOsebaEmso() );
        oseba.setOsebaEmail( osebaDto.getOsebaEmail() );
        oseba.setOsebaTelefon( osebaDto.getOsebaTelefon() );
    }

    @Override
    public List<OsebaDto> mapOsebaToDtoList(List<Oseba> osebe) {
        if ( osebe == null ) {
            return null;
        }

        List<OsebaDto> list = new ArrayList<OsebaDto>( osebe.size() );
        for ( Oseba oseba : osebe ) {
            list.add( convertToOsebaDto( oseba ) );
        }

        return list;
    }

    private String osebaIzkaznicaIzkaznicaOznaka(Oseba oseba) {
        if ( oseba == null ) {
            return null;
        }
        Izkaznica izkaznica = oseba.getIzkaznica();
        if ( izkaznica == null ) {
            return null;
        }
        String izkaznicaOznaka = izkaznica.getIzkaznicaOznaka();
        if ( izkaznicaOznaka == null ) {
            return null;
        }
        return izkaznicaOznaka;
    }
}
