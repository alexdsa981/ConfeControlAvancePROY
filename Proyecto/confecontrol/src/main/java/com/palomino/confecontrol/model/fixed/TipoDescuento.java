package com.palomino.confecontrol.model.fixed;

import jakarta.persistence.*;

@Entity
public class TipoDescuento {
    public TipoDescuento() {
    }

    @Id
    @Column(name = "id_detalle_descuento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
