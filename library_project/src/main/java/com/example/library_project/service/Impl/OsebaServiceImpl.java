package com.example.library_project.service.Impl;

import com.example.library_project.controllers.IzkaznicaController;
import com.example.library_project.dto.IzkaznicaDto;
import com.example.library_project.dto.OsebaDto;
import com.example.library_project.entities.Izkaznica;
import com.example.library_project.entities.Oseba;
import com.example.library_project.enums.OsebaSpol;
import com.example.library_project.enums.Status;
import com.example.library_project.mappers.IzkaznicaMapper;
import com.example.library_project.mappers.OsebaMapper;
import com.example.library_project.repository.IzkaznicaRepository;
import com.example.library_project.repository.OsebaRepository;
import com.example.library_project.service.IzkaznicaService;
import com.example.library_project.service.OsebaService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class OsebaServiceImpl implements OsebaService {
    private final OsebaMapper osebaMapper;
    private final OsebaRepository osebaRepository;
    private final IzkaznicaService izkaznicaService;
    private final IzkaznicaMapper izkaznicaMapper;
    private final IzkaznicaRepository izkaznicaRepository;
    private final IzkaznicaController izkaznicaController;

    @Override
    public List<OsebaDto> findAll() {
        return osebaMapper.mapOsebaToDtoList(osebaRepository.findAll());
    }

    @Override
    public OsebaDto returnByOsebaEmail(String email) {
        Oseba oseba =
                osebaRepository
                        .findByOsebaEmail(email)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Oseba ne obstaja z email: " + email));

        return osebaMapper.convertToOsebaDto(oseba);
    }

    @Override
    public OsebaDto createOseba(OsebaSpol spol, OsebaDto osebaDto) {
        Oseba oseba = osebaMapper.mapDtoToOseba(osebaDto);
        IzkaznicaDto izkaznicaDto = izkaznicaService.createIzkaznica();

        if (osebaRepository.existsByOsebaEmail(osebaDto.getOsebaEmail())) {
            throw new IllegalArgumentException(
                    "Oseba že obstaja z email računom: " + osebaDto.getOsebaEmail());
        }
        if (osebaRepository.existsByOsebaEmso(osebaDto.getOsebaEmso())) {
            throw new IllegalArgumentException(
                    "Oseba že obstaja z emso: " + osebaDto.getOsebaEmso());
        }
        if (osebaRepository.existsByOsebaTelefon(osebaDto.getOsebaTelefon())) {
            throw new IllegalArgumentException(
                    "Oseba že obstaja z telefonsko številko: " + osebaDto.getOsebaTelefon());
        }

        Izkaznica izkaznica =
                izkaznicaRepository
                        .findByIzkaznicaOznaka(izkaznicaDto.getIzkaznicaOznaka())
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Izkaznica ne obstaja z oznako: "
                                                        + izkaznicaDto.getIzkaznicaOznaka()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = osebaDto.getOsebaDatumRojstva();

        oseba.setOsebaStatus(Status.ACTIVE);
        oseba.setOsebaSpol(spol);
        oseba.setOsebaDatumRojstva(date.format(formatter));
        oseba.setIzkaznica(izkaznica);

        return osebaMapper.convertToOsebaDto(osebaRepository.save(oseba));
    }

    @Override
    public OsebaDto updateOseba(long emso, OsebaDto osebaDto) {
        Oseba oseba =
                osebaRepository
                        .findByOsebaEmso(emso)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Oseba ne obstaja z EMŠO: " + emso));

        osebaMapper.updateValuesOdExistingOseba(osebaDto, oseba);

        return osebaMapper.convertToOsebaDto(osebaRepository.save(oseba));
    }

    @Override
    public OsebaDto deleteOseba(long emso) {
        Oseba oseba =
                osebaRepository
                        .findByOsebaEmso(emso)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Oseba ne obstaja z EMŠO: " + emso));

        oseba.setOsebaStatus(Status.INACTIVE);

        return osebaMapper.convertToOsebaDto(osebaRepository.save(oseba));
    }
}
