package com.example.library_project.service;

import com.example.library_project.dto.AvtorDto;
import java.util.List;

public interface AvtorService {

    List<AvtorDto> findAll();

    AvtorDto returnAvtorByAvtorId(long id);

    AvtorDto createAvtor(AvtorDto avtorDto);

    AvtorDto updateAvtor(long id, AvtorDto avtorDto);
}
