package com.example.library_project.service;

import com.example.library_project.dto.IzposojaDto;
import java.util.List;

public interface IzposojaService {

    List<IzposojaDto> findAll();

    List<IzposojaDto> returnIzposojaByUserUsername(String username);

    IzposojaDto returnIzposojaByIzposojaId(long id);

    IzposojaDto createNewIzposoja(IzposojaDto izposojaDto);
}
