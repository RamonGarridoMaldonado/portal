package com.rgarmal.portal.services.Impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rgarmal.portal.model.Alumno;
import com.rgarmal.portal.services.AlumnoService;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Value("${url.matriculacion.rest.service}")
    String urlApi;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Alumno> findAll() {
        Alumno[] ca = restTemplate.getForObject(urlApi + "alumnos", Alumno[].class);
        List<Alumno> alumnos = Arrays.asList(ca);
        return alumnos;
    }

    @Override
    public Alumno find(int codigo) {
        Alumno alumno = restTemplate.getForObject(urlApi + "alumnos/" + codigo, Alumno.class);
        return alumno;
    }

    @Override
    public Alumno save(Alumno alumno) {
        Alumno alumnoR = restTemplate.postForObject(urlApi + "alumnos", alumno, Alumno.class);
        return alumnoR;
    }

    @Override
    public void update(Alumno alumno) {
        restTemplate.put(urlApi + "alumnos/" + alumno.getCodigo(), alumno);
    }

    @Override
    public void delete(int codigo) {
        restTemplate.delete(urlApi + "alumnos/" + codigo);
    }
    
}
