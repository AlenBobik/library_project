package com.example.library_project.mappers;

import com.example.library_project.dto.ZaposlenDto;
import com.example.library_project.entities.Zaposlen;
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
public class ZaposlenMapperImpl implements ZaposlenMapper {

    @Override
    public ZaposlenDto convertToZaposlenDto(Zaposlen zaposlen) {
        if ( zaposlen == null ) {
            return null;
        }

        ZaposlenDto zaposlenDto = new ZaposlenDto();

        zaposlenDto.setZaposlenOznakaPogodbe( zaposlen.getZaposlenOznakaPogodbe() );
        zaposlenDto.setZaposlenOd( zaposlen.getZaposlenOd() );
        zaposlenDto.setZaposlenDo( zaposlen.getZaposlenDo() );

        return zaposlenDto;
    }

    @Override
    public Zaposlen mapDtoToZaposlen(ZaposlenDto zaposlenDto) {
        if ( zaposlenDto == null ) {
            return null;
        }

        Zaposlen zaposlen = new Zaposlen();

        zaposlen.setZaposlenOznakaPogodbe( zaposlenDto.getZaposlenOznakaPogodbe() );
        zaposlen.setZaposlenOd( zaposlenDto.getZaposlenOd() );
        zaposlen.setZaposlenDo( zaposlenDto.getZaposlenDo() );

        return zaposlen;
    }

    @Override
    public void updateValuesOfExistingZaposlen(ZaposlenDto zaposlenDto, Zaposlen zaposlen) {
        if ( zaposlenDto == null ) {
            return;
        }

        zaposlen.setZaposlenOd( zaposlenDto.getZaposlenOd() );
        zaposlen.setZaposlenDo( zaposlenDto.getZaposlenDo() );
    }

    @Override
    public List<ZaposlenDto> mapZaposlenToDtoList(List<Zaposlen> zaposleni) {
        if ( zaposleni == null ) {
            return null;
        }

        List<ZaposlenDto> list = new ArrayList<ZaposlenDto>( zaposleni.size() );
        for ( Zaposlen zaposlen : zaposleni ) {
            list.add( convertToZaposlenDto( zaposlen ) );
        }

        return list;
    }
}
