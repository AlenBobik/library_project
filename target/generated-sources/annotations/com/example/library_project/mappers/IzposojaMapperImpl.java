package com.example.library_project.mappers;

import com.example.library_project.dto.IzposojaDto;
import com.example.library_project.entities.Izposoja;
import com.example.library_project.entities.KnjigaIzvod;
import com.example.library_project.entities.User;
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
public class IzposojaMapperImpl implements IzposojaMapper {

    @Override
    public IzposojaDto convertToIzposojaDto(Izposoja izposoja) {
        if ( izposoja == null ) {
            return null;
        }

        IzposojaDto izposojaDto = new IzposojaDto();

        izposojaDto.setKnjigaIzvodIsbn( izposojaKnjigaIzvodKnjigaIzvodIsbn( izposoja ) );
        izposojaDto.setUserUsername( izposojaUserUsername( izposoja ) );
        izposojaDto.setIzposojaId( izposoja.getIzposojaId() );
        izposojaDto.setIzposojaOpomba( izposoja.getIzposojaOpomba() );
        if ( izposoja.getIzposojaDatumIzposoje() != null ) {
            izposojaDto.setIzposojaDatumIzposoje( LocalDate.parse( izposoja.getIzposojaDatumIzposoje() ) );
        }
        if ( izposoja.getIzposojaDatumPoteka() != null ) {
            izposojaDto.setIzposojaDatumPoteka( LocalDate.parse( izposoja.getIzposojaDatumPoteka() ) );
        }

        return izposojaDto;
    }

    @Override
    public Izposoja mapToIzposoja(IzposojaDto izposojaDto) {
        if ( izposojaDto == null ) {
            return null;
        }

        Izposoja izposoja = new Izposoja();

        izposoja.setIzposojaOpomba( izposojaDto.getIzposojaOpomba() );
        if ( izposojaDto.getIzposojaDatumIzposoje() != null ) {
            izposoja.setIzposojaDatumIzposoje( DateTimeFormatter.ISO_LOCAL_DATE.format( izposojaDto.getIzposojaDatumIzposoje() ) );
        }
        if ( izposojaDto.getIzposojaDatumPoteka() != null ) {
            izposoja.setIzposojaDatumPoteka( DateTimeFormatter.ISO_LOCAL_DATE.format( izposojaDto.getIzposojaDatumPoteka() ) );
        }

        return izposoja;
    }

    @Override
    public void updateValuesOfExistingIzposoja(IzposojaDto izposojaDto, Izposoja izposoja) {
        if ( izposojaDto == null ) {
            return;
        }

        izposoja.setIzposojaOpomba( izposojaDto.getIzposojaOpomba() );
        if ( izposojaDto.getIzposojaDatumIzposoje() != null ) {
            izposoja.setIzposojaDatumIzposoje( DateTimeFormatter.ISO_LOCAL_DATE.format( izposojaDto.getIzposojaDatumIzposoje() ) );
        }
        else {
            izposoja.setIzposojaDatumIzposoje( null );
        }
        if ( izposojaDto.getIzposojaDatumPoteka() != null ) {
            izposoja.setIzposojaDatumPoteka( DateTimeFormatter.ISO_LOCAL_DATE.format( izposojaDto.getIzposojaDatumPoteka() ) );
        }
        else {
            izposoja.setIzposojaDatumPoteka( null );
        }
    }

    @Override
    public List<IzposojaDto> mapIzposojaToDtoList(List<Izposoja> izposoja) {
        if ( izposoja == null ) {
            return null;
        }

        List<IzposojaDto> list = new ArrayList<IzposojaDto>( izposoja.size() );
        for ( Izposoja izposoja1 : izposoja ) {
            list.add( convertToIzposojaDto( izposoja1 ) );
        }

        return list;
    }

    private String izposojaKnjigaIzvodKnjigaIzvodIsbn(Izposoja izposoja) {
        if ( izposoja == null ) {
            return null;
        }
        KnjigaIzvod knjigaIzvod = izposoja.getKnjigaIzvod();
        if ( knjigaIzvod == null ) {
            return null;
        }
        String knjigaIzvodIsbn = knjigaIzvod.getKnjigaIzvodIsbn();
        if ( knjigaIzvodIsbn == null ) {
            return null;
        }
        return knjigaIzvodIsbn;
    }

    private String izposojaUserUsername(Izposoja izposoja) {
        if ( izposoja == null ) {
            return null;
        }
        User user = izposoja.getUser();
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
