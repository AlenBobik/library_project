package com.example.library_project.mappers;

import com.example.library_project.dto.KnjigaDto;
import com.example.library_project.entities.Knjiga;
import com.example.library_project.entities.KnjigaIzvod;
import com.example.library_project.entities.Knjiznica;
import com.example.library_project.enums.KnjigaStatus;
import com.example.library_project.enums.KnjigaVrstaKnjige;
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
public class KnjigaMapperImpl implements KnjigaMapper {

    @Override
    public KnjigaDto convertToKnjigaDto(Knjiga knjiga) {
        if ( knjiga == null ) {
            return null;
        }

        KnjigaDto knjigaDto = new KnjigaDto();

        knjigaDto.setKnjigaIzvodIsbn( knjigaKnjigaIzvodKnjigaIzvodIsbn( knjiga ) );
        knjigaDto.setKnjiznicaId( knjigaKnjiznicaKnjiznicaId( knjiga ) );
        knjigaDto.setKnjigaUuid( knjiga.getKnjigaUuid() );
        if ( knjiga.getKnjigaStatus() != null ) {
            knjigaDto.setKnjigaStatus( knjiga.getKnjigaStatus().name() );
        }
        if ( knjiga.getKnjigaVrstaKnjige() != null ) {
            knjigaDto.setKnjigaVrstaKnjige( knjiga.getKnjigaVrstaKnjige().name() );
        }
        knjigaDto.setKnjigaJezik( knjiga.getKnjigaJezik() );
        knjigaDto.setKnjigaIzdaja( knjiga.getKnjigaIzdaja() );

        return knjigaDto;
    }

    @Override
    public Knjiga mapDtoToKnjiga(KnjigaDto knjigaDto) {
        if ( knjigaDto == null ) {
            return null;
        }

        Knjiga knjiga = new Knjiga();

        knjiga.setKnjigaUuid( knjigaDto.getKnjigaUuid() );
        if ( knjigaDto.getKnjigaStatus() != null ) {
            knjiga.setKnjigaStatus( Enum.valueOf( KnjigaStatus.class, knjigaDto.getKnjigaStatus() ) );
        }
        if ( knjigaDto.getKnjigaVrstaKnjige() != null ) {
            knjiga.setKnjigaVrstaKnjige( Enum.valueOf( KnjigaVrstaKnjige.class, knjigaDto.getKnjigaVrstaKnjige() ) );
        }
        knjiga.setKnjigaJezik( knjigaDto.getKnjigaJezik() );
        knjiga.setKnjigaIzdaja( knjigaDto.getKnjigaIzdaja() );

        return knjiga;
    }

    @Override
    public void updateValuesOfExistingKnjiga(KnjigaDto knjigaDto, Knjiga knjiga) {
        if ( knjigaDto == null ) {
            return;
        }

        if ( knjigaDto.getKnjigaVrstaKnjige() != null ) {
            knjiga.setKnjigaVrstaKnjige( Enum.valueOf( KnjigaVrstaKnjige.class, knjigaDto.getKnjigaVrstaKnjige() ) );
        }
        else {
            knjiga.setKnjigaVrstaKnjige( null );
        }
        knjiga.setKnjigaJezik( knjigaDto.getKnjigaJezik() );
        knjiga.setKnjigaIzdaja( knjigaDto.getKnjigaIzdaja() );
    }

    @Override
    public List<KnjigaDto> mapKnjigaToDtoList(List<Knjiga> knjige) {
        if ( knjige == null ) {
            return null;
        }

        List<KnjigaDto> list = new ArrayList<KnjigaDto>( knjige.size() );
        for ( Knjiga knjiga : knjige ) {
            list.add( convertToKnjigaDto( knjiga ) );
        }

        return list;
    }

    private String knjigaKnjigaIzvodKnjigaIzvodIsbn(Knjiga knjiga) {
        if ( knjiga == null ) {
            return null;
        }
        KnjigaIzvod knjigaIzvod = knjiga.getKnjigaIzvod();
        if ( knjigaIzvod == null ) {
            return null;
        }
        String knjigaIzvodIsbn = knjigaIzvod.getKnjigaIzvodIsbn();
        if ( knjigaIzvodIsbn == null ) {
            return null;
        }
        return knjigaIzvodIsbn;
    }

    private long knjigaKnjiznicaKnjiznicaId(Knjiga knjiga) {
        if ( knjiga == null ) {
            return 0L;
        }
        Knjiznica knjiznica = knjiga.getKnjiznica();
        if ( knjiznica == null ) {
            return 0L;
        }
        long knjiznicaId = knjiznica.getKnjiznicaId();
        return knjiznicaId;
    }
}
