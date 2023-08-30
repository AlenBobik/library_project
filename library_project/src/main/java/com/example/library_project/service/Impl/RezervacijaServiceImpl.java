package com.example.library_project.service.Impl;

import com.example.library_project.dto.RezervacijaDto;
import com.example.library_project.entities.KnjigaIzvod;
import com.example.library_project.entities.Rezervacija;
import com.example.library_project.entities.User;
import com.example.library_project.mappers.RezervacijaMapper;
import com.example.library_project.repository.KnjigaIzvodRepository;
import com.example.library_project.repository.RezervacijaRepository;
import com.example.library_project.repository.UserRepository;
import com.example.library_project.service.RezervacijaService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class RezervacijaServiceImpl implements RezervacijaService {

    private final RezervacijaMapper rezervacijaMapper;
    private final RezervacijaRepository rezervacijaRepository;

    private final UserRepository userRepository;
    private final KnjigaIzvodRepository knjigaIzvodRepository;

    @Override
    public List<RezervacijaDto> findAll() {
        return rezervacijaMapper.mapRezervacijaToDtoList(rezervacijaRepository.findAll());
    }

    @Override
    public List<RezervacijaDto> returnRezervacijaByUserUsername(String username) {
        return rezervacijaMapper.mapRezervacijaToDtoList(
                rezervacijaRepository.findAllByUserUsername(username));
    }

    @Override
    public RezervacijaDto returnRezervacijaById(long id) {
        Rezervacija rezervacija =
                rezervacijaRepository
                        .findByRezervacijaId(id)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Rezervacija ne obstaja z id: " + id));

        return rezervacijaMapper.convertToRezervacijaDto(rezervacija);
    }

    @Override
    public RezervacijaDto createNewRezervacija(RezervacijaDto rezervacijaDto) {
        Rezervacija rezervacija = rezervacijaMapper.mapToRezervacija(rezervacijaDto);

        User user =
                userRepository
                        .findByUsername(rezervacijaDto.getUserUsername())
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "User ne obstaja z uporabniškim imenom: "
                                                        + rezervacijaDto.getUserUsername()));

        KnjigaIzvod knjigaIzvod =
                knjigaIzvodRepository
                        .findByKnjigaIzvodIsbn(rezervacijaDto.getKnjigaIzvodIsbn())
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Knjiga ne obstaja z isbn oznako: "
                                                        + rezervacijaDto.getKnjigaIzvodIsbn()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now();
        LocalDate futureDate = date.plusDays(30);

        rezervacija.setRezervacijaDatumRezervacije(date.format(formatter));
        rezervacija.setRezervacijaDatumPoteka(futureDate.format(formatter));
        rezervacija.setUser(user);
        rezervacija.setKnjigaIzvod(knjigaIzvod);

        return rezervacijaMapper.convertToRezervacijaDto(rezervacijaRepository.save(rezervacija));
    }
}
