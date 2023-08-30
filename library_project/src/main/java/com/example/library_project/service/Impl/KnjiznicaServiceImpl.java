package com.example.library_project.service.Impl;

import com.example.library_project.dto.KnjiznicaDto;
import com.example.library_project.entities.Knjiznica;
import com.example.library_project.enums.KnjiznicaStatus;
import com.example.library_project.mappers.KnjiznicaMapper;
import com.example.library_project.repository.KnjiznicaRepository;
import com.example.library_project.service.KnjiznicaService;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class KnjiznicaServiceImpl implements KnjiznicaService {

    private final KnjiznicaMapper knjiznicaMapper;
    private final KnjiznicaRepository knjiznicaRepository;

    @Override
    public List<KnjiznicaDto> findAll() {
        return knjiznicaMapper.mapKnjiznicaToDtoList(knjiznicaRepository.findAll());
    }

    @Override
    public KnjiznicaDto returnKnjiznicaByKnjiznicaId(long knjiznica_id) {
        Knjiznica knjiznica =
                knjiznicaRepository
                        .findByKnjiznicaId(knjiznica_id)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "knjiznica ne obstaja z ID oznako: "
                                                        + knjiznica_id));

        return knjiznicaMapper.convertToKnjiznicaDto(knjiznica);
    }

    @Override
    public KnjiznicaDto createKnjiznica(KnjiznicaDto knjiznicaDto) {
        Knjiznica knjiznica = knjiznicaMapper.mapDtoToKnjiznica(knjiznicaDto);

        knjiznica.setKnjiznicaStatus(KnjiznicaStatus.AKTIVNA_LOKACIJA);

        return knjiznicaMapper.convertToKnjiznicaDto(knjiznicaRepository.save(knjiznica));
    }

    @Override
    public KnjiznicaDto updateKnjiznica(long knjiznica_id, KnjiznicaDto knjiznicaDto) {
        Knjiznica knjiznica =
                knjiznicaRepository
                        .findByKnjiznicaId(knjiznica_id)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Knjiznica ne obstaja z ID oznako: "
                                                        + knjiznica_id));

        knjiznicaMapper.updateValuesOfExistingknjiznica(knjiznicaDto, knjiznica);

        return knjiznicaMapper.convertToKnjiznicaDto(knjiznicaRepository.save(knjiznica));
    }

    @Override
    public KnjiznicaDto deleteKnjiznica(long knjiznica_id) {
        Knjiznica knjiznica =
                knjiznicaRepository
                        .findByKnjiznicaId(knjiznica_id)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Knjiznica ne obstaja z ID oznako: "
                                                        + knjiznica_id));

        knjiznica.setKnjiznicaStatus(KnjiznicaStatus.NEAKTIVNA_LOKACIJA);

        return knjiznicaMapper.convertToKnjiznicaDto(knjiznicaRepository.save(knjiznica));
    }
}
