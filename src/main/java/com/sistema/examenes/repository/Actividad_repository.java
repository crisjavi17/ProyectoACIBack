package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Actividad;
import com.sistema.examenes.entity.Criterio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Actividad_repository extends JpaRepository<Actividad, Long> {

    @Query(value = "SELECT * from actividad where visible =true", nativeQuery = true)
    List<Actividad> listarActividad();


    @Query(value = "SELECT ac.id_actividad, ac.descripcion, ac.fecha_fin, ac.fecha_inicio, ac.nombre, ac.visible, ac.id_evidencia, ac.usuario_id\n"
            + "FROM actividad ac \n"
            + "JOIN evidencia e ON ac.id_evidencia = e.id_evidencia\n"
            + "JOIN indicador i ON e.indicador_id_indicador = i.id_indicador\n"
            + "JOIN asignacion_indicador ag ON ag.indicador_id_indicador = i.id_indicador\n"
            + "LEFT JOIN ponderacion po ON i.id_indicador = po.indicador_id_indicador\n"
            + "WHERE po.indicador_id_indicador IS NULL\n"
            + "AND ag.modelo_id_modelo = (SELECT MAX(id_modelo) FROM modelo)\n"
            + "AND ac.fecha_fin < CURRENT_DATE;", nativeQuery = true)
    List<Actividad> listarActividadAtrasadas();

    @Query(value = "SELECT ac.id_actividad, ac.descripcion, ac.fecha_fin, ac.fecha_inicio, ac.nombre, ac.visible, ac.evidencia_id_evidencia, ac.usuario_id\n" +
"FROM actividad ac \n" +
"JOIN evidencia e ON ac.evidencia_id_evidencia = e.id_evidencia\n" +
"JOIN indicador i ON e.indicador_id_indicador = i.id_indicador\n" +
"JOIN asignacion_indicador ag ON ag.indicador_id_indicador = i.id_indicador\n" +
"JOIN ponderacion po ON i.id_indicador = po.indicador_id_indicador\n" +
"WHERE ag.modelo_id_modelo = (SELECT MAX(id_modelo) FROM modelo);", nativeQuery = true)
    List<Actividad> listarActividadCumplidas();
    
    @Query(value = "SELECT ac.id_actividad, ac.descripcion, ac.fecha_fin, ac.fecha_inicio, ac.nombre, ac.visible, ac.id_evidencia, ac.usuario_id\n" +
"FROM actividad ac, evidencia e, detalle_evaluacion d, asignacion_indicador ag, indicador i\n" +
"WHERE ac.id_evidencia = e.id_evidencia\n" +
"AND e.indicador_id_indicador = i.id_indicador\n" +
"AND d.evidencia_id_evidencia = ac.id_evidencia\n" +
"AND ag.indicador_id_indicador = i.id_indicador\n" +
"AND d.estado = false\n" +
"AND ag.modelo_id_modelo = (SELECT MAX(id_modelo) FROM modelo)", nativeQuery = true)
    List<Actividad> listarEvideRechazadasFecha();
    
@Query(value = "SELECT * FROM actividad ac JOIN evidencia ev ON ac.id_evidencia=ev.id_evidencia\n" +
        "JOIN indicador i ON i.id_indicador = ev.indicador_id_indicador\n" +
        "JOIN ponderacion po ON po.indicador_id_indicador=i.id_indicador\n" +
        "JOIN modelo mo ON mo.id_modelo=po.modelo_id_modelo WHERE\n" +
        "mo.id_modelo=(SELECT MAX(id_modelo) FROM modelo) AND ac.estado != 'Aprobada'\n" +
        "AND ac.visible=true;", nativeQuery = true)
List<Actividad> actividadRechazada();

    @Query(value = "SELECT * FROM actividad ac JOIN evidencia ev ON ac.id_evidencia=ev.id_evidencia\n" +
            "JOIN indicador i ON i.id_indicador = ev.indicador_id_indicador\n" +
            "JOIN ponderacion po ON po.indicador_id_indicador=i.id_indicador\n" +
            "JOIN modelo mo ON mo.id_modelo=po.modelo_id_modelo WHERE\n" +
            "mo.id_modelo=(SELECT MAX(id_modelo) FROM modelo)\n" +
            "AND ac.visible=true;", nativeQuery = true)
    List<Actividad> actividadCont();

    @Query(value = "SELECT * FROM actividad ac JOIN evidencia ev ON ac.id_evidencia=ev.id_evidencia\n" +
            "JOIN indicador i ON i.id_indicador = ev.indicador_id_indicador\n" +
            "JOIN ponderacion po ON po.indicador_id_indicador=i.id_indicador\n" +
            "JOIN modelo mo ON mo.id_modelo=po.modelo_id_modelo WHERE\n" +
            "mo.id_modelo=(SELECT MAX(id_modelo) FROM modelo)\n" +
            "AND ac.visible=true AND ac.usuario_id=:id;", nativeQuery = true)
    List<Actividad> actividadUsu(Long id);

    @Query(value = "SELECT * FROM actividad ac JOIN evidencia ev ON ac.id_evidencia=ev.id_evidencia\n" +
            "JOIN indicador i ON i.id_indicador = ev.indicador_id_indicador\n" +
            "JOIN ponderacion po ON po.indicador_id_indicador=i.id_indicador\n" +
            "JOIN modelo mo ON mo.id_modelo=po.modelo_id_modelo WHERE\n" +
            "mo.id_modelo=(SELECT MAX(id_modelo) FROM modelo) AND ac.estado = 'Aprobada'\n" +
            "AND ac.visible=true;", nativeQuery = true)
    List<Actividad> actividadAprobada();
    @Query(value = "select * from  actividad ac JOIN usuarios u ON ac.usuario_id = u.id where u.username=:username and ac.visible =true",nativeQuery = true)
    List<Actividad>listarporusuario(String username);
    List<Actividad> findByNombreContainingIgnoreCase(String nombre);

    @Query(value = "SELECT * FROM actividad WHERE visible= true AND id_evidencia=:idEvidendicia ;",nativeQuery = true)
    List<Actividad>listarporEvidencia(Long idEvidendicia);


}
