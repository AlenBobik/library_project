package com.example.library_project.service.Impl;

import com.example.library_project.dto.IzkaznicaDto;
import com.example.library_project.entities.Izkaznica;
import com.example.library_project.enums.Status;
import com.example.library_project.mappers.IzkaznicaMapper;
import com.example.library_project.repository.IzkaznicaRepository;
import com.example.library_project.service.IzkaznicaService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class IzkaznicaServiceImpl implements IzkaznicaService {
    private final IzkaznicaMapper izkaznicaMapper;
    private final IzkaznicaRepository izkaznicaRepository;

    @Override
    public List<IzkaznicaDto> findAll() {
        return izkaznicaMapper.mapIzkaznicaToDtoList(izkaznicaRepository.findAll());
    }

    @Override
    public IzkaznicaDto returnIzkaznicaByIzkaznicaOznaka(String oznaka) {
        Izkaznica izkaznica =
                izkaznicaRepository
                        .findByIzkaznicaOznaka(oznaka)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Izkaznica ne obstaja z oznako: " + oznaka));

        return izkaznicaMapper.convertToizkaznicaDto(izkaznica);
    }

    @Override
    public IzkaznicaDto createIzkaznica() {
        long oznaka = 10001;

        while (izkaznicaRepository.existsByIzkaznicaOznaka(String.valueOf(oznaka))) {
            oznaka++;
        }

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Izkaznica izkaznica = new Izkaznica();

        izkaznica.setIzkaznicaOznaka(String.valueOf(oznaka));
        izkaznica.setIzkaznicaStatus(Status.ACTIVE);
        izkaznica.setIzkaznicaDatumIzdaje(currentDate.format(formatter));
        izkaznica.setIzkaznicaDatumpoteka(currentDate.plusYears(1).format(formatter));

        return izkaznicaMapper.convertToizkaznicaDto(izkaznicaRepository.save(izkaznica));
    }

    @Override
    public IzkaznicaDto updateIzkaznica(String oznaka, IzkaznicaDto izkaznicaDto) {
        Izkaznica izkaznica =
                izkaznicaRepository
                        .findByIzkaznicaOznaka(oznaka)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Izkaznica ne obstaja z oznako: " + oznaka));

        izkaznicaMapper.updateValuesOfExistingIzkaznica(izkaznicaDto, izkaznica);

        return izkaznicaMapper.convertToizkaznicaDto(izkaznicaRepository.save(izkaznica));
    }

    @Override
    public IzkaznicaDto deleteIzkaznica(String oznaka) {
        Izkaznica izkaznica =
                izkaznicaRepository
                        .findByIzkaznicaOznaka(oznaka)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Izkaznica ne obstaja z oznako: " + oznaka));

        izkaznica.setIzkaznicaStatus(Status.INACTIVE);

        return izkaznicaMapper.convertToizkaznicaDto(izkaznicaRepository.save(izkaznica));
    }
}
