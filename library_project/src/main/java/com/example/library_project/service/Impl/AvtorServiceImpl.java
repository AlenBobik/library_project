package com.example.library_project.service.Impl;

import com.example.library_project.dto.AvtorDto;
import com.example.library_project.entities.Avtor;
import com.example.library_project.mappers.AvtorMapper;
import com.example.library_project.repository.AvtorRepository;
import com.example.library_project.service.AvtorService;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class AvtorServiceImpl implements AvtorService {
    private final AvtorMapper avtorMapper;
    private final AvtorRepository avtorRepository;

    @Override
    public List<AvtorDto> findAll() {
        return avtorMapper.mapAvtorToDtoList(avtorRepository.findAll());
    }

    @Override
    public AvtorDto returnAvtorByAvtorId(long id) {
        Avtor avtor =
                avtorRepository
                        .findByAvtorId(id)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Avtor ne obstaja z ID oznako: " + id));

        return avtorMapper.convertToAvtorDto(avtorRepository.save(avtor));
    }

    @Override
    public AvtorDto createAvtor(AvtorDto avtorDto) {
        Avtor avtor = avtorMapper.mapDtoToAvtor(avtorDto);

        return avtorMapper.convertToAvtorDto(avtorRepository.save(avtor));
    }

    @Override
    public AvtorDto updateAvtor(long id, AvtorDto avtorDto) {
        Avtor avtor =
                avtorRepository
                        .findByAvtorId(id)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Avtor ne obstaja z ID oznako: " + id));

        avtorMapper.updateValuesOfExistingAvtor(avtorDto, avtor);

        return avtorMapper.convertToAvtorDto(avtorRepository.save(avtor));
    }
}
