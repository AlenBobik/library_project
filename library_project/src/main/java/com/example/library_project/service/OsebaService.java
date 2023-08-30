package com.example.library_project.service;

import com.example.library_project.dto.OsebaDto;
import com.example.library_project.enums.OsebaSpol;
import java.util.List;

public interface OsebaService {

    List<OsebaDto> findAll();

    OsebaDto returnByOsebaEmail(String email);

    OsebaDto createOseba(OsebaSpol spol, OsebaDto osebaDto);

    OsebaDto updateOseba(long emso, OsebaDto osebaDto);

    OsebaDto deleteOseba(long emso);
}
