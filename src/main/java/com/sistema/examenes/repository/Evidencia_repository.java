package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Actividad;
import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.entity.Evidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Evidencia_repository extends JpaRepository<Evidencia, Long> {
    @Query(value = "SELECT * from evidencia where visible =true", nativeQuery = true)
    List<Evidencia> listarEvidencia();

    @Query(value = "SELECT * from evidencia e JOIN asignacion_evidencia ae ON ae.evidencia_id_evidencia = e.id_evidencia\n"
            +
            "JOIN usuarios u ON ae.usuario_id = u.id where u.username=:username and e.visible =true", nativeQuery = true)
    public List<Evidencia> evidenciaUsuario(String username);

   /* @Query(value = " SELECT e.* FROM evidencia e  \n" +
            "               LEFT JOIN asignacion_evidencia ae ON e.id_evidencia = ae.evidencia_id_evidencia \n" +
            "               WHERE ae.id_asignacion_evidencia IS NULL AND e.visible=true",nativeQuery = true)*/

    @Query(value = "SELECT e.* FROM evidencia e " +
            "JOIN indicador i ON e.indicador_id_indicador = i.id_indicador " +
            "JOIN subcriterio sc ON i.subcriterio_id_subcriterio = sc.id_subcriterio " +
            "JOIN criterio c ON sc.id_criterio = c.id_criterio " +
            "JOIN asignacion_admin aa ON c.id_criterio = aa.criterio_id_criterio " +
            "WHERE aa.usuario_id =:idUser AND e.visible = true " +
            "AND e.id_evidencia NOT IN (SELECT evidencia_id_evidencia FROM asignacion_evidencia)", nativeQuery = true)
    List<Evidencia> listarEvidenciaAsigna(Long idUser);




    // SELECT evidencia.*
    // FROM public.indicador join public.evidencia ON
    // evidencia.indicador_id_indicador = indicador.id_indicador
    // WHERE evidencia.indicador_id_indicador=6 And evidencia.visible=true;
    @Query(value = "SELECT evidencia.* FROM public.indicador join public.evidencia ON evidencia.indicador_id_indicador = indicador.id_indicador WHERE evidencia.indicador_id_indicador=:id_indicador And evidencia.visible=true", nativeQuery = true)
    List<Evidencia> listarEvidenciaPorIndicador(Long id_indicador);
    
}
