package com.example.library_project.service;

import com.example.library_project.dto.KnjiznaPolicaDto;
import java.util.List;

public interface KnjiznaPolicaService {

    List<KnjiznaPolicaDto> findAll();

    KnjiznaPolicaDto returnKnjiznaPolicaByKnjiznaPolicaOznaka(String oznaka);

    KnjiznaPolicaDto createKnjiznaPolica(KnjiznaPolicaDto knjiznaPolicaDto);

    KnjiznaPolicaDto updateKnjiznaPolica(long id, KnjiznaPolicaDto knjiznaPolicaDto);
}
