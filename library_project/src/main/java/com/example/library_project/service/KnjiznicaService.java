package com.example.library_project.service;

import com.example.library_project.dto.KnjiznicaDto;
import java.util.List;

public interface KnjiznicaService {

    List<KnjiznicaDto> findAll();

    KnjiznicaDto returnKnjiznicaByKnjiznicaId(long knjiznica_id);

    KnjiznicaDto createKnjiznica(KnjiznicaDto knjiznicaDto);

    KnjiznicaDto updateKnjiznica(long knjiznica_id, KnjiznicaDto knjiznicaDto);

    KnjiznicaDto deleteKnjiznica(long knjiznica_id);
}
