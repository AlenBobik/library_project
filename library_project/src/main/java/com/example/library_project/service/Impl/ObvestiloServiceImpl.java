package com.example.library_project.service.Impl;

import com.example.library_project.dto.ObvestiloDto;
import com.example.library_project.entities.Obvestilo;
import com.example.library_project.mappers.ObvestiloMapper;
import com.example.library_project.repository.ObvestiloRepository;
import com.example.library_project.service.ObvestiloService;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class ObvestiloServiceImpl implements ObvestiloService {
    private final ObvestiloMapper obvestiloMapper;
    private final ObvestiloRepository obvestiloRepository;

    @Override
    public List<ObvestiloDto> findAll() {
        return obvestiloMapper.mapObvestiloToDtoList(obvestiloRepository.findAll());
    }

    @Override
    public ObvestiloDto returnObvestiloByObvestiloId(long id) {
        Obvestilo obvestilo =
                obvestiloRepository
                        .findByObvestiloId(id)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Obvestilo ne obstaja z ID oznako: " + id));

        return obvestiloMapper.convertToObvestiloDto(obvestilo);
    }

    @Override
    public ObvestiloDto createObvestilo(ObvestiloDto obvestiloDto) {
        Obvestilo obvestilo = obvestiloMapper.mapDtoToObvestilo(obvestiloDto);

        return obvestiloMapper.convertToObvestiloDto(obvestiloRepository.save(obvestilo));
    }

    @Override
    public ObvestiloDto updateObvestilo(long id, ObvestiloDto obvestiloDto) {
        Obvestilo obvestilo =
                obvestiloRepository
                        .findByObvestiloId(id)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Obvestilo ne obstaja z ID oznako: " + id));

        obvestiloMapper.updateValuesOfExistingObvestilo(obvestiloDto, obvestilo);
        return obvestiloMapper.convertToObvestiloDto(obvestiloRepository.save(obvestilo));
    }
}
