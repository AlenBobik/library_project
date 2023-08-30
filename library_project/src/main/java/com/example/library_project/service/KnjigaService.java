package com.example.library_project.service;

import com.example.library_project.dto.KnjigaDto;
import java.util.List;
import java.util.UUID;

public interface KnjigaService {

    List<KnjigaDto> findAll();

    KnjigaDto returnKnjigaByKnjigaUuid(UUID uuid);

    KnjigaDto createKnjiga(KnjigaDto knjigaDto);

    KnjigaDto updateKnjiga(UUID uuid, KnjigaDto knjigaDto);

    KnjigaDto deleteKnjiga(UUID uuid);
}
