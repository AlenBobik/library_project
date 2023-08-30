package com.example.library_project.service.Impl;

import com.example.library_project.dto.KnjigaIzvodDto;
import com.example.library_project.entities.Avtor;
import com.example.library_project.entities.KnjigaIzvod;
import com.example.library_project.mappers.KnjigaIzvodMapper;
import com.example.library_project.repository.AvtorRepository;
import com.example.library_project.repository.KnjigaIzvodRepository;
import com.example.library_project.service.KnjigaIzvodService;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class KnjigaIzvodServiceImpl implements KnjigaIzvodService {

    private final KnjigaIzvodMapper knjigaIzvodMapper;
    private final KnjigaIzvodRepository knjigaIzvodRepository;
    private final AvtorRepository avtorRepository;

    @Override
    public List<KnjigaIzvodDto> findAll() {
        return knjigaIzvodMapper.mapKnjigaIzvodToDtoList(knjigaIzvodRepository.findAll());
    }

    @Override
    public KnjigaIzvodDto returnKnjigaIzvodByKnjigaIzvodIsbn(String isbn) {
        KnjigaIzvod izvod =
                knjigaIzvodRepository
                        .findByKnjigaIzvodIsbn(isbn)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "KnjigaIzvod ne obstaja z ISBN oznako: " + isbn));

        return knjigaIzvodMapper.convertToKnjigaIzvodDto(izvod);
    }

    @Override
    public List<KnjigaIzvodDto> returnKnjigaIzvodByKnjigaIzvodNaslov(String ime) {
        return knjigaIzvodMapper.mapKnjigaIzvodToDtoList(
                knjigaIzvodRepository.findAllByKnjigaIzvodNaslov(ime));
    }

    @Override
    public KnjigaIzvodDto createKnjigaIzvod(KnjigaIzvodDto knjigaIzvodDto) {
        KnjigaIzvod izvod = knjigaIzvodMapper.mapToKnjigaIzvod(knjigaIzvodDto);

        if (knjigaIzvodRepository.existsByKnjigaIzvodIsbn(
                String.valueOf(knjigaIzvodDto.getKnjigaIzvodIsbn()))) {
            throw new IllegalArgumentException(
                    "Knjiga že obstaja z ISBN oznako: " + knjigaIzvodDto.getKnjigaIzvodIsbn());
        }

        return knjigaIzvodMapper.convertToKnjigaIzvodDto(knjigaIzvodRepository.save(izvod));
    }

    @Override
    public KnjigaIzvodDto updateKnjigaIzvod(String isbn, KnjigaIzvodDto knjigaIzvodDto) {
        KnjigaIzvod knjigaIzvod =
                knjigaIzvodRepository
                        .findByKnjigaIzvodIsbn(isbn)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "KnjigaIzvod ne obstaja z ISBN oznako: " + isbn));

        knjigaIzvodMapper.updateValuesOfExistingKnjigaIzvod(knjigaIzvodDto, knjigaIzvod);

        return knjigaIzvodMapper.convertToKnjigaIzvodDto(knjigaIzvodRepository.save(knjigaIzvod));
    }

    @Override
    public KnjigaIzvodDto dodajAvtorjaKnjigiIzvod(String isbn, long avtorId) {
        List<Avtor> avtorSet = null;

        KnjigaIzvod knjigaIzvod =
                knjigaIzvodRepository
                        .findByKnjigaIzvodIsbn(isbn)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "KnjigaIzvod ne obstaja z ISBN oznako: " + isbn));

        Avtor avtor =
                avtorRepository
                        .findByAvtorId(avtorId)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Avtor ne obstaja z ID oznako: " + avtorId));

        avtorSet = knjigaIzvod.getAvtor();
        avtorSet.add(avtor);

        knjigaIzvod.setAvtor(avtorSet);

        return knjigaIzvodMapper.convertToKnjigaIzvodDto(knjigaIzvodRepository.save(knjigaIzvod));
    }
}
