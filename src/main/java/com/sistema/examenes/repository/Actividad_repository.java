package com.sistema.examenes.repository;

import com.sistema.examenes.entity.Actividad;
import com.sistema.examenes.entity.Criterio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Actividad_repository extends JpaRepository<Actividad, Long> {

    @Query(value = "SELECT * from actividad where visible =true", nativeQuery = true)
    List<Actividad> listarActividad();


    @Query(value = "SELECT ac.*\n"
            + "FROM actividad ac \n"
            + "JOIN evidencia e ON ac.id_evidencia = e.id_evidencia\n"
            + "JOIN indicador i ON e.indicador_id_indicador = i.id_indicador\n"
            + "JOIN asignacion_indicador ag ON ag.indicador_id_indicador = i.id_indicador\n"
            + "LEFT JOIN ponderacion po ON i.id_indicador = po.indicador_id_indicador\n"
            + "WHERE po.indicador_id_indicador IS NULL\n"
            + "AND ag.modelo_id_modelo = (SELECT MAX(id_modelo) FROM modelo)\n"
            + "AND ac.fecha_fin < CURRENT_DATE;", nativeQuery = true)
    List<Actividad> listarActividadAtrasadas();

    @Query(value = "SELECT ac.*\n" +
"FROM actividad ac JOIN evidencia e\n" +
"ON ac.id_evidencia = e.id_evidencia\n" +
"JOIN indicador i\n" +
"ON e.indicador_id_indicador = i.id_indicador\n" +
"JOIN asignacion_indicador ag\n" +
"ON ag.indicador_id_indicador = i.id_indicador\n" +
"WHERE ac.estado = 'Aprobada'\n" +
"AND ag.modelo_id_modelo = (SELECT MAX(id_modelo) FROM modelo)", nativeQuery = true)
    List<Actividad> listarActividadCumplidas();
    
    @Query(value = "SELECT ac.*\n" +
"FROM actividad ac JOIN evidencia e\n" +
"ON ac.id_evidencia = e.id_evidencia\n" +
"JOIN indicador i\n" +
"ON e.indicador_id_indicador = i.id_indicador\n" +
"JOIN asignacion_indicador ag\n" +
"ON ag.indicador_id_indicador = i.id_indicador\n" +
"WHERE ac.estado = 'Rechazada'\n" +
"AND ag.modelo_id_modelo = (SELECT MAX(id_modelo) FROM modelo)", nativeQuery = true)
    List<Actividad> listarEvideRechazadasFecha();


    @Query(value = "  SELECT distinct u.id idpersona, per.primer_nombre,per.primer_apellido , COALESCE(per.correo, 'Sin correo') AS percorreo\n" +
            "        FROM actividad ac\n" +
            "        JOIN evidencia e ON e.id_evidencia = ac.id_evidencia\n" +
            "        JOIN usuarios u ON u.id=ac.usuario_id\n" +
            "        JOIN persona per ON per.id_persona = u.persona_id_persona\n" +
            "        JOIN indicador i ON i.id_indicador = e.indicador_id_indicador\n" +
            "        JOIN ponderacion po ON po.indicador_id_indicador = i.id_indicador\n" +
            "        JOIN modelo mo ON mo.id_modelo = po.modelo_id_modelo\n" +
            "        WHERE mo.id_modelo = (SELECT MAX(id_modelo) FROM modelo);\n",nativeQuery = true)
    List<Actividad>listarByActividad();

    @Query(value = "select * from  actividad ac JOIN usuarios u ON ac.usuario_id = u.id where u.username=:username and ac.visible =true",nativeQuery = true)
    List<Actividad>listarporusuario(String username);
    List<Actividad> findByNombreContainingIgnoreCase(String nombre);

    @Query(value = "SELECT * FROM actividad WHERE visible= true AND id_evidencia=:idEvidendicia ;",nativeQuery = true)
    List<Actividad>listarporEvidencia(Long idEvidendicia);

    @Query(value = "SELECT * FROM actividad WHERE usuario_id = :idUsuario ;",nativeQuery = true)
    List<Actividad>listarByUsuario(Long idUsuario);

}


