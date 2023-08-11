package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Asignacion_Admin;
import com.sistema.examenes.projection.AsignacionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Asignacion_Admin_repository extends JpaRepository<Asignacion_Admin, Long> {
    @Query(value = "SELECT * from asignacion_admin where visible =true", nativeQuery = true)
    List<Asignacion_Admin> listarAsignacion_Admin();
    @Query(value = "SELECT DISTINCT u.id as enc, per.primer_nombre||' '||per.primer_apellido as nombrescri, cri.nombre as actividasi\n" +
            "FROM asignacion_admin aa JOIN usuarios u ON aa.usuario_id=u.id\n" +
            "JOIN persona per ON per.id_persona=u.persona_id_persona\n" +
            "JOIN criterio cri ON cri.id_criterio=aa.criterio_id_criterio\n" +
            "JOIN subcriterio s ON s.id_criterio=cri.id_criterio\n" +
            "JOIN indicador i ON i.subcriterio_id_subcriterio= s.id_subcriterio\n" +
            "JOIN ponderacion po ON po.indicador_id_indicador=i.id_indicador\n" +
            "JOIN modelo mo ON mo.id_modelo=po.modelo_id_modelo AND mo.id_modelo=:id_modelo \n" +
            "WHERE aa.visible=CAST(:veri AS BOOLEAN) ORDER BY u.id;", nativeQuery = true)
    List<AsignacionProjection> asignacionAdmin(Long id_modelo, String veri);

    @Query(value = "SELECT * from asignacion_admin where criterio_id_criterio = :id_usuario and visible =true", nativeQuery = true)
    Asignacion_Admin listarAsignacion_AdminPorUsuario(Long id_usuario);

    @Query(value = "SELECT * from asignacion_admin where criterio_id_criterio = :id_criterio and usuario_id = :id_usuario", nativeQuery = true)
    Asignacion_Admin listarAsignacion_AdminPorUsuarioCriterio(Long id_criterio, Long id_usuario);
}
