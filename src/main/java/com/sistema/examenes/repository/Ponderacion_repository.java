package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.entity.Ponderacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface Ponderacion_repository extends JpaRepository<Ponderacion, Long> {
    @Query(value = "SELECT * from ponderacion where visible =true", nativeQuery = true)
    List<Ponderacion> listarPonderacion();

    // SELECT p.*
    // FROM public.ponderacion p join public.modelo m ON m.id_modelo =
    // p.modelo_id_modelo
    // WHERE m.id_modelo=1;
    @Query(value = "SELECT DISTINCT p.fecha FROM ponderacion p JOIN modelo m ON m.id_modelo = p.modelo_id_modelo WHERE m.id_modelo = :id_modelo", nativeQuery = true)
    List<Date> listarPonderacionPorModelo(Long id_modelo);

    // SELECT p.*
    // FROM public.ponderacion p
    // JOIN public.modelo m ON m.id_modelo = p.modelo_id_modelo
    // WHERE p.fecha = TO_TIMESTAMP('2023-05-25 05:10:58.747', 'YYYY-MM-DD
    // HH:MI:SS.MS');
    // @Query(value = "SELECT p.* from ponderacion p join modelo m on m.id_modelo =
    // p.modelo_id_modelo where p.fecha=TO_TIMESTAMP(:fecha, 'YYYY-MM-DD
    // HH:MI:SS.MS')", nativeQuery = true)
    // List<Ponderacion> listarPonderacionPorFecha(Date fecha);

    // SELECT id_ponderacion, fecha, peso, porc_obtenido, porc_utilida_obtenida,
    // valor_obtenido, visible, indicador_id_indicador, modelo_id_modelo
    // FROM public.ponderacion
    // WHERE ponderacion.fecha = TO_DATE('2023-05-25', 'YYYY-MM-DD');
    @Query(value = "SELECT p.* FROM ponderacion p JOIN modelo m ON m.id_modelo = p.modelo_id_modelo WHERE p.fecha = TO_DATE(:fecha, 'YYYY-MM-DD')", nativeQuery = true)
    List<Ponderacion> listarPorFecha(@Param("fecha") String fecha);

    // SELECT p.*
    // FROM public.ponderacion p
    // JOIN public.modelo m ON m.id_modelo = p.modelo_id_modelo
    // WHERE p.fecha = TO_TIMESTAMP('2023-05-25 05:10:58.747', 'YYYY-MM-DD
    // HH:MI:SS.MS');
    // @Query(value = "SELECT p.* from ponderacion p join modelo m on m.id_modelo =
    // p.modelo_id_modelo where p.fecha=TO_TIMESTAMP(:fecha, 'YYYY-MM-DD
    // HH:MI:SS.MS')", nativeQuery = true)
    // List<Ponderacion> listarPonderacionPorFecha(Date fecha);

    @Query(value = "SELECT p.* FROM ponderacion p JOIN modelo m ON m.id_modelo = p.modelo_id_modelo WHERE p.fecha = TO_DATE(:fecha, 'YYYY-MM-DD')", nativeQuery = true)
    List<Ponderacion> listarPonderacionPorFecha(@Param("fecha") String fecha);

    // SELECT id_ponderacion, fecha, peso, porc_obtenido, porc_utilida_obtenida,
    // valor_obtenido, visible, indicador_id_indicador, modelo_id_modelo
    // FROM public.ponderacion
    // WHERE ponderacion.fecha = TO_DATE('2023-05-25', 'YYYY-MM-DD');

    /*
     * SELECT p.*
     * FROM public.ponderacion p join public.modelo m ON p.modelo_id_modelo =
     * m.id_modelo
     * where id_modelo=3;
     */

    @Query(value = "SELECT p.* FROM ponderacion p JOIN modelo m ON p.modelo_id_modelo = m.id_modelo WHERE m.id_modelo = :id_modelo", nativeQuery = true)
    List<Ponderacion> listarPondeModelo(@Param("id_modelo") Long id_modelo);
}
