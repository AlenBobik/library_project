package com.example.library_project.service;

import com.example.library_project.dto.ObvestiloDto;
import java.util.List;

public interface ObvestiloService {

    List<ObvestiloDto> findAll();

    ObvestiloDto returnObvestiloByObvestiloId(long id);

    ObvestiloDto createObvestilo(ObvestiloDto obvestiloDto);

    ObvestiloDto updateObvestilo(long id, ObvestiloDto obvestiloDto);
}
