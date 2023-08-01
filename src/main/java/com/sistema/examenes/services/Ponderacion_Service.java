package com.sistema.examenes.services;

import com.sistema.examenes.entity.Ponderacion;

import java.util.Date;
import java.util.List;

public interface Ponderacion_Service extends GenericService<Ponderacion, Long> {
    public List<Ponderacion> listar();

    public List<Date> listarPonderacionPorModelo(Long id_modelo);

    public List<Ponderacion> listarPonderacionPorFecha(String fecha);

    public List<Ponderacion> listarPorFecha(String fecha);

}
