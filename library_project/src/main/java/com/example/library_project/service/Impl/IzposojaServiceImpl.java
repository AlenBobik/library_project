package com.example.library_project.service.Impl;

import com.example.library_project.dto.IzposojaDto;
import com.example.library_project.entities.Izposoja;
import com.example.library_project.entities.KnjigaIzvod;
import com.example.library_project.entities.User;
import com.example.library_project.mappers.IzposojaMapper;
import com.example.library_project.repository.IzposojaRepository;
import com.example.library_project.repository.KnjigaIzvodRepository;
import com.example.library_project.repository.UserRepository;
import com.example.library_project.service.IzposojaService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class IzposojaServiceImpl implements IzposojaService {

    private final IzposojaMapper izposojaMapper;
    private final IzposojaRepository izposojaRepository;

    private final UserRepository userRepository;
    private final KnjigaIzvodRepository knjigaIzvodRepository;

    @Override
    public List<IzposojaDto> findAll() {
        return izposojaMapper.mapIzposojaToDtoList(izposojaRepository.findAll());
    }

    @Override
    public List<IzposojaDto> returnIzposojaByUserUsername(String username) {
        return izposojaMapper.mapIzposojaToDtoList(
                izposojaRepository.findAllByUserUsername(username));
    }

    @Override
    public IzposojaDto returnIzposojaByIzposojaId(long id) {
        Izposoja izposoja =
                izposojaRepository
                        .findByIzposojaId(id)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Izposoja ne obstaja z id: " + id));

        return izposojaMapper.convertToIzposojaDto(izposoja);
    }

    @Override
    public IzposojaDto createNewIzposoja(IzposojaDto izposojaDto) {
        Izposoja izposoja = izposojaMapper.mapToIzposoja(izposojaDto);

        User user =
                userRepository
                        .findByUsername(izposojaDto.getUserUsername())
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "User ne obstaja z uporabniškim imenom: "
                                                        + izposojaDto.getUserUsername()));

        KnjigaIzvod knjigaIzvod =
                knjigaIzvodRepository
                        .findByKnjigaIzvodIsbn(izposojaDto.getKnjigaIzvodIsbn())
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Knjiga ne obstaja z isbn oznako: "
                                                        + izposojaDto.getKnjigaIzvodIsbn()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now();
        LocalDate futureDate = date.plusDays(30);

        izposoja.setIzposojaDatumIzposoje(date.format(formatter));
        izposoja.setIzposojaDatumPoteka(futureDate.format(formatter));
        izposoja.setUser(user);
        izposoja.setKnjigaIzvod(knjigaIzvod);

        return izposojaMapper.convertToIzposojaDto(izposojaRepository.save(izposoja));
    }
}
