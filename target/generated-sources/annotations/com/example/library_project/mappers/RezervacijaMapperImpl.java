package com.example.library_project.mappers;

import com.example.library_project.dto.RezervacijaDto;
import com.example.library_project.entities.KnjigaIzvod;
import com.example.library_project.entities.Rezervacija;
import com.example.library_project.entities.User;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-05T13:57:04+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class RezervacijaMapperImpl implements RezervacijaMapper {

    @Override
    public RezervacijaDto convertToRezervacijaDto(Rezervacija rezervacija) {
        if ( rezervacija == null ) {
            return null;
        }

        RezervacijaDto rezervacijaDto = new RezervacijaDto();

        rezervacijaDto.setKnjigaIzvodIsbn( rezervacijaKnjigaIzvodKnjigaIzvodIsbn( rezervacija ) );
        rezervacijaDto.setUserUsername( rezervacijaUserUsername( rezervacija ) );
        rezervacijaDto.setRezervacijaId( rezervacija.getRezervacijaId() );
        rezervacijaDto.setRezervacijaOpomba( rezervacija.getRezervacijaOpomba() );
        if ( rezervacija.getRezervacijaDatumRezervacije() != null ) {
            rezervacijaDto.setRezervacijaDatumRezervacije( LocalDate.parse( rezervacija.getRezervacijaDatumRezervacije() ) );
        }
        if ( rezervacija.getRezervacijaDatumPoteka() != null ) {
            rezervacijaDto.setRezervacijaDatumPoteka( LocalDate.parse( rezervacija.getRezervacijaDatumPoteka() ) );
        }

        return rezervacijaDto;
    }

    @Override
    public Rezervacija mapToRezervacija(RezervacijaDto rezervacijaDto) {
        if ( rezervacijaDto == null ) {
            return null;
        }

        Rezervacija rezervacija = new Rezervacija();

        rezervacija.setRezervacijaOpomba( rezervacijaDto.getRezervacijaOpomba() );
        if ( rezervacijaDto.getRezervacijaDatumRezervacije() != null ) {
            rezervacija.setRezervacijaDatumRezervacije( DateTimeFormatter.ISO_LOCAL_DATE.format( rezervacijaDto.getRezervacijaDatumRezervacije() ) );
        }
        if ( rezervacijaDto.getRezervacijaDatumPoteka() != null ) {
            rezervacija.setRezervacijaDatumPoteka( DateTimeFormatter.ISO_LOCAL_DATE.format( rezervacijaDto.getRezervacijaDatumPoteka() ) );
        }

        return rezervacija;
    }

    @Override
    public void updateValuesOfExistingRezervacija(RezervacijaDto rezervacijaDto, Rezervacija rezervacija) {
        if ( rezervacijaDto == null ) {
            return;
        }

        rezervacija.setRezervacijaOpomba( rezervacijaDto.getRezervacijaOpomba() );
        if ( rezervacijaDto.getRezervacijaDatumRezervacije() != null ) {
            rezervacija.setRezervacijaDatumRezervacije( DateTimeFormatter.ISO_LOCAL_DATE.format( rezervacijaDto.getRezervacijaDatumRezervacije() ) );
        }
        else {
            rezervacija.setRezervacijaDatumRezervacije( null );
        }
        if ( rezervacijaDto.getRezervacijaDatumPoteka() != null ) {
            rezervacija.setRezervacijaDatumPoteka( DateTimeFormatter.ISO_LOCAL_DATE.format( rezervacijaDto.getRezervacijaDatumPoteka() ) );
        }
        else {
            rezervacija.setRezervacijaDatumPoteka( null );
        }
    }

    @Override
    public List<RezervacijaDto> mapRezervacijaToDtoList(List<Rezervacija> rezervacija) {
        if ( rezervacija == null ) {
            return null;
        }

        List<RezervacijaDto> list = new ArrayList<RezervacijaDto>( rezervacija.size() );
        for ( Rezervacija rezervacija1 : rezervacija ) {
            list.add( convertToRezervacijaDto( rezervacija1 ) );
        }

        return list;
    }

    private String rezervacijaKnjigaIzvodKnjigaIzvodIsbn(Rezervacija rezervacija) {
        if ( rezervacija == null ) {
            return null;
        }
        KnjigaIzvod knjigaIzvod = rezervacija.getKnjigaIzvod();
        if ( knjigaIzvod == null ) {
            return null;
        }
        String knjigaIzvodIsbn = knjigaIzvod.getKnjigaIzvodIsbn();
        if ( knjigaIzvodIsbn == null ) {
            return null;
        }
        return knjigaIzvodIsbn;
    }

    private String rezervacijaUserUsername(Rezervacija rezervacija) {
        if ( rezervacija == null ) {
            return null;
        }
        User user = rezervacija.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }
}
