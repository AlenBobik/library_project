package com.example.library_project.service;

import com.example.library_project.dto.ZaposlenDto;
import java.util.List;

public interface ZaposlenService {

    List<ZaposlenDto> findAll();

    ZaposlenDto returnZaposlenByzaposlenOznakaPogodbe(String oznaka);

    ZaposlenDto createZaposlen(ZaposlenDto zaposlenDto);

    ZaposlenDto updateZaposlen(String oznaka, ZaposlenDto zaposlenDto);
}
