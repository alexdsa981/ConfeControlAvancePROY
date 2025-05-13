package com.palomino.confecontrol.model.dynamic;

import com.palomino.confecontrol.model.fixed.OperacionPrenda;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class DetalleTrabajo {
    @Id
    @Column(name = "id_detalle_trabajo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;


    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "detalle_paquete_lote_id", nullable = false)
    private DetallePaqueteLote detallePaqueteLote;

    @Column(nullable = false)
    private BigDecimal monto;


}
