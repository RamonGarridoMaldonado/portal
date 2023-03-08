package com.rgarmal.portal.services;

import java.util.List;

import com.rgarmal.portal.model.Alumno;

public interface AlumnoService {
    public List<Alumno> findAll();
    public Alumno find(int codigo);
    public Alumno save(Alumno alumno);
    public void update(Alumno alumno);
    public void delete(int codigo);
}
