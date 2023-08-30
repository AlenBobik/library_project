package com.example.library_project.service.Impl;

import com.example.library_project.dto.ZaposlenDto;
import com.example.library_project.entities.Zaposlen;
import com.example.library_project.mappers.ZaposlenMapper;
import com.example.library_project.repository.ZaposlenRepository;
import com.example.library_project.service.ZaposlenService;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ZaposlenServiceImpl implements ZaposlenService {

    private final ZaposlenMapper zaposlenMapper;
    private final ZaposlenRepository zaposlenRepository;

    @Override
    public List<ZaposlenDto> findAll() {
        return zaposlenMapper.mapZaposlenToDtoList(zaposlenRepository.findAll());
    }

    @Override
    public ZaposlenDto returnZaposlenByzaposlenOznakaPogodbe(String oznaka) {
        Zaposlen zaposlen =
                zaposlenRepository
                        .findByZaposlenOznakaPogodbe(oznaka)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "zaposlen ne obstaja z oznako pogodbe: " + oznaka));

        return zaposlenMapper.convertToZaposlenDto(zaposlen);
    }

    @Override
    public ZaposlenDto createZaposlen(ZaposlenDto zaposlenDto) {
        Zaposlen zaposlen = zaposlenMapper.mapDtoToZaposlen(zaposlenDto);

        return zaposlenMapper.convertToZaposlenDto(zaposlenRepository.save(zaposlen));
    }

    @Override
    public ZaposlenDto updateZaposlen(String oznaka, ZaposlenDto zaposlenDto) {
        Zaposlen zaposlen =
                zaposlenRepository
                        .findByZaposlenOznakaPogodbe(oznaka)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Zaposlen ne obstaja z oznako pogodbe: " + oznaka));

        zaposlenMapper.updateValuesOfExistingZaposlen(zaposlenDto, zaposlen);

        return zaposlenMapper.convertToZaposlenDto(zaposlenRepository.save(zaposlen));
    }
}
