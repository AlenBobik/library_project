package com.example.library_project.service;

import com.example.library_project.dto.RezervacijaDto;
import java.util.List;

public interface RezervacijaService {

    List<RezervacijaDto> findAll();

    List<RezervacijaDto> returnRezervacijaByUserUsername(String username);

    RezervacijaDto returnRezervacijaById(long id);

    RezervacijaDto createNewRezervacija(RezervacijaDto rezervacijaDto);
}
