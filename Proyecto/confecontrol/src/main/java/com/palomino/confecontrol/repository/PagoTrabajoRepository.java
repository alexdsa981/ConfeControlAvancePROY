package com.palomino.confecontrol.repository;

import com.palomino.confecontrol.model.dynamic.PagoTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoTrabajoRepository extends JpaRepository<PagoTrabajo, Long> {

    //lo que se hará es almacenar una vez pagado
}
