package com.example.library_project.service;

import com.example.library_project.dto.KnjigaIzvodDto;
import java.util.List;

public interface KnjigaIzvodService {

    List<KnjigaIzvodDto> findAll();

    KnjigaIzvodDto returnKnjigaIzvodByKnjigaIzvodIsbn(String isbn);

    List<KnjigaIzvodDto> returnKnjigaIzvodByKnjigaIzvodNaslov(String ime);

    KnjigaIzvodDto createKnjigaIzvod(KnjigaIzvodDto knjigaIzvodDto);

    KnjigaIzvodDto updateKnjigaIzvod(String isbn, KnjigaIzvodDto knjigaIzvodDto);

    KnjigaIzvodDto dodajAvtorjaKnjigiIzvod(String knjigaIzvodisbn, long avtorId);
}
