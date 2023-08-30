package com.example.library_project.service;

import com.example.library_project.dto.IzkaznicaDto;
import java.util.List;

public interface IzkaznicaService {

    List<IzkaznicaDto> findAll();

    IzkaznicaDto returnIzkaznicaByIzkaznicaOznaka(String oznaka);

    IzkaznicaDto createIzkaznica();

    IzkaznicaDto updateIzkaznica(String oznaka, IzkaznicaDto izkaznicaDto);

    IzkaznicaDto deleteIzkaznica(String oznaka);
}
