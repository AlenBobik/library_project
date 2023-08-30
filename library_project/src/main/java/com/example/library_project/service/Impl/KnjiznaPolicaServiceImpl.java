package com.example.library_project.service.Impl;

import com.example.library_project.dto.KnjiznaPolicaDto;
import com.example.library_project.entities.KnjiznaPolica;
import com.example.library_project.mappers.KnjiznaPolicaMapper;
import com.example.library_project.repository.KnjiznaPolicaRepository;
import com.example.library_project.service.KnjiznaPolicaService;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class KnjiznaPolicaServiceImpl implements KnjiznaPolicaService {

    private final KnjiznaPolicaMapper knjiznaPolicaMapper;
    private final KnjiznaPolicaRepository knjiznaPolicaRepository;

    @Override
    public List<KnjiznaPolicaDto> findAll() {
        return knjiznaPolicaMapper.mapKnjiznaPolicaToDtoList(knjiznaPolicaRepository.findAll());
    }

    @Override
    public KnjiznaPolicaDto returnKnjiznaPolicaByKnjiznaPolicaOznaka(String oznaka) {
        KnjiznaPolica knjiznaPolica =
                knjiznaPolicaRepository
                        .findByKnjiznaPolicaOznaka(oznaka)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Knjizna polica ne obstaja z oznako: " + oznaka));

        return knjiznaPolicaMapper.convertToKnjiznaPolicaDto(
                knjiznaPolicaRepository.save(knjiznaPolica));
    }

    @Override
    public KnjiznaPolicaDto createKnjiznaPolica(KnjiznaPolicaDto knjiznaPolicaDto) {
        KnjiznaPolica knjiznaPolica = knjiznaPolicaMapper.mapToKnjiznaPolica(knjiznaPolicaDto);

        if (getKnjiznaPolicaRepository()
                .existsByKnjiznaPolicaOznaka(knjiznaPolicaDto.getKnjiznaPolicaOznaka())) {
            throw new IllegalArgumentException(
                    "Knjizna polica ne obstaja z oznako: "
                            + knjiznaPolicaDto.getKnjiznaPolicaOznaka());
        }

        return knjiznaPolicaMapper.convertToKnjiznaPolicaDto(
                knjiznaPolicaRepository.save(knjiznaPolica));
    }

    @Override
    public KnjiznaPolicaDto updateKnjiznaPolica(long id, KnjiznaPolicaDto knjiznaPolicaDto) {
        KnjiznaPolica knjiznaPolica =
                knjiznaPolicaRepository
                        .findByKnjiznaPolicaId(id)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Knjizna polica ne obstaja z ID oznako: " + id));

        knjiznaPolicaMapper.updateValuesOfExistingKnjiznaPolica(knjiznaPolicaDto, knjiznaPolica);

        return knjiznaPolicaMapper.convertToKnjiznaPolicaDto(
                knjiznaPolicaRepository.save(knjiznaPolica));
    }
}
