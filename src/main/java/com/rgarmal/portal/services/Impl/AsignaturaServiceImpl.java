package com.rgarmal.portal.services.Impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rgarmal.portal.model.Asignatura;
import com.rgarmal.portal.services.AsignaturaService;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Value("${url.matriculacion.rest.service}")
    String urlApi;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Asignatura> findAll() {
        Asignatura[] ca = restTemplate.getForObject(urlApi + "asignaturas", Asignatura[].class);
        List<Asignatura> asignaturas = Arrays.asList(ca);
        return asignaturas;
    }

    @Override
    public Asignatura find(int codigo) {
        Asignatura asignatura = restTemplate.getForObject(urlApi + "asignaturas/" + codigo, Asignatura.class);
        return asignatura;
    }

    @Override
    public Asignatura save(Asignatura asignatura) {
        Asignatura asignatura2 = restTemplate.postForObject(urlApi + "asignaturas", asignatura, Asignatura.class);
        return asignatura2;
    }

    @Override
    public void update(Asignatura asignatura) {
        restTemplate.put(urlApi + "asignaturas/" + asignatura.getCodigo(), asignatura);
    }

    @Override
    public void delete(int codigo) {
        restTemplate.delete(urlApi + "asignaturas/" + codigo);
    }
}
