package com.palomino.confecontrol.repository;

import com.palomino.confecontrol.model.dynamic.DetallePaqueteLote;
import com.palomino.confecontrol.model.dynamic.DetalleTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetalleTrabajoRepository extends JpaRepository<DetalleTrabajo, Long> {

}
