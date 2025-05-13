package com.palomino.confecontrol.model.fixed;

import jakarta.persistence.*;

@Entity
public class TipoDescuento {
    @Id
    @Column(name = "id_detalle_descuento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;
}
