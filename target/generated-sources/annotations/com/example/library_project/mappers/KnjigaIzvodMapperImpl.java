package com.example.library_project.mappers;

import com.example.library_project.dto.KnjigaIzvodDto;
import com.example.library_project.entities.KnjigaIzvod;
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
public class KnjigaIzvodMapperImpl implements KnjigaIzvodMapper {

    @Override
    public KnjigaIzvodDto convertToKnjigaIzvodDto(KnjigaIzvod knjigaIzvod) {
        if ( knjigaIzvod == null ) {
            return null;
        }

        KnjigaIzvodDto knjigaIzvodDto = new KnjigaIzvodDto();

        knjigaIzvodDto.setKnjigaIzvodNaslov( knjigaIzvod.getKnjigaIzvodNaslov() );
        knjigaIzvodDto.setKnjigaIzvodIsbn( knjigaIzvod.getKnjigaIzvodIsbn() );
        knjigaIzvodDto.setKnjigaIzvodDatumIzdaje( knjigaIzvod.getKnjigaIzvodDatumIzdaje() );

        return knjigaIzvodDto;
    }

    @Override
    public KnjigaIzvod mapToKnjigaIzvod(KnjigaIzvodDto knjigaIzvodDto) {
        if ( knjigaIzvodDto == null ) {
            return null;
        }

        KnjigaIzvod knjigaIzvod = new KnjigaIzvod();

        knjigaIzvod.setKnjigaIzvodNaslov( knjigaIzvodDto.getKnjigaIzvodNaslov() );
        knjigaIzvod.setKnjigaIzvodIsbn( knjigaIzvodDto.getKnjigaIzvodIsbn() );
        knjigaIzvod.setKnjigaIzvodDatumIzdaje( knjigaIzvodDto.getKnjigaIzvodDatumIzdaje() );

        return knjigaIzvod;
    }

    @Override
    public void updateValuesOfExistingKnjigaIzvod(KnjigaIzvodDto knjigaIzvodDto, KnjigaIzvod knjigaIzvod) {
        if ( knjigaIzvodDto == null ) {
            return;
        }

        knjigaIzvod.setKnjigaIzvodNaslov( knjigaIzvodDto.getKnjigaIzvodNaslov() );
        knjigaIzvod.setKnjigaIzvodIsbn( knjigaIzvodDto.getKnjigaIzvodIsbn() );
        knjigaIzvod.setKnjigaIzvodDatumIzdaje( knjigaIzvodDto.getKnjigaIzvodDatumIzdaje() );
    }

    @Override
    public List<KnjigaIzvodDto> mapKnjigaIzvodToDtoList(List<KnjigaIzvod> knjigaIzvod) {
        if ( knjigaIzvod == null ) {
            return null;
        }

        List<KnjigaIzvodDto> list = new ArrayList<KnjigaIzvodDto>( knjigaIzvod.size() );
        for ( KnjigaIzvod knjigaIzvod1 : knjigaIzvod ) {
            list.add( convertToKnjigaIzvodDto( knjigaIzvod1 ) );
        }

        return list;
    }
}
