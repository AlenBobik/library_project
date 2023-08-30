package com.example.library_project.service.Impl;

import com.example.library_project.dto.KnjigaDto;
import com.example.library_project.entities.Knjiga;
import com.example.library_project.entities.KnjigaIzvod;
import com.example.library_project.entities.Knjiznica;
import com.example.library_project.enums.KnjigaStatus;
import com.example.library_project.mappers.KnjigaMapper;
import com.example.library_project.repository.KnjigaIzvodRepository;
import com.example.library_project.repository.KnjigaRepository;
import com.example.library_project.repository.KnjiznicaRepository;
import com.example.library_project.service.KnjigaService;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class KnjigaServiceImpl implements KnjigaService {

    private final KnjigaMapper knjigaMapper;
    private final KnjigaRepository knjigaRepository;

    private final KnjigaIzvodRepository knjigaIzvodRepository;
    private final KnjiznicaRepository knjiznicaRepository;

    @Override
    public List<KnjigaDto> findAll() {
        return knjigaMapper.mapKnjigaToDtoList(knjigaRepository.findAll());
    }

    @Override
    public KnjigaDto returnKnjigaByKnjigaUuid(UUID uuid) {
        Knjiga knjiga =
                knjigaRepository
                        .findByKnjigaUuid(uuid)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Knjiga ne obstaja z UUID oznako: " + uuid));

        return knjigaMapper.convertToKnjigaDto(knjiga);
    }

    @Override
    public KnjigaDto createKnjiga(KnjigaDto knjigaDto) {
        Knjiga knjiga = knjigaMapper.mapDtoToKnjiga(knjigaDto);

        KnjigaIzvod knjigaIzvod =
                knjigaIzvodRepository
                        .findByKnjigaIzvodIsbn(knjigaDto.getKnjigaIzvodIsbn())
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "KnjigaIzvod ne obstaja z ISBN oznako: "
                                                        + knjigaDto.getKnjigaIzvodIsbn()));

        Knjiznica knjiznica =
                knjiznicaRepository
                        .findByKnjiznicaId(knjigaDto.getKnjiznicaId())
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Knjiznica ne obstaja z ID oznako: "
                                                        + knjigaDto.getKnjiznicaId()));

        knjiga.setKnjigaUuid(UUID.randomUUID());
        knjiga.setKnjigaStatus(KnjigaStatus.NA_VOLJO);
        knjiga.setKnjigaIzvod(knjigaIzvod);
        knjiga.setKnjiznica(knjiznica);

        return knjigaMapper.convertToKnjigaDto(knjigaRepository.save(knjiga));
    }

    @Override
    public KnjigaDto updateKnjiga(UUID uuid, KnjigaDto knjigaDto) {
        Knjiga knjiga =
                knjigaRepository
                        .findByKnjigaUuid(uuid)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Knjiga ne obstaja z oznako UUID: " + uuid));

        knjigaMapper.updateValuesOfExistingKnjiga(knjigaDto, knjiga);
        return knjigaMapper.convertToKnjigaDto(knjigaRepository.save(knjiga));
    }

    @Override
    public KnjigaDto deleteKnjiga(UUID uuid) {
        Knjiga knjiga =
                knjigaRepository
                        .findByKnjigaUuid(uuid)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "knjiga ne obstaja z UUID oznako: " + uuid));

        if (knjiga.getKnjigaStatus().equals(KnjigaStatus.NI_V_UPORABI)) {
            throw new IllegalArgumentException("Knjiga ni več v uporabi z UUID oznako: " + uuid);
        }

        knjiga.setKnjigaStatus(KnjigaStatus.NI_V_UPORABI);

        return knjigaMapper.convertToKnjigaDto(knjigaRepository.save(knjiga));
    }
}
