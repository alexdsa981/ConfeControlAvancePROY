package com.palomino.confecontrol.model.fixed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class OperacionPrenda {

    public OperacionPrenda() {
    }


    @Id
    @Column(name = "id_operacion_prenda")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private BigDecimal precioNormal;
    @Column(nullable = false)
    private BigDecimal precioHorasExtra;
    @Column(nullable = false)
    private BigDecimal precioFeriado;

    @Column(nullable = false)
    private Boolean isActive;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "prenda_id", nullable = false)
    private Prenda prenda;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Prenda getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda prenda) {
        this.prenda = prenda;
    }

    public BigDecimal getPrecioFeriado() {
        return precioFeriado;
    }

    public void setPrecioFeriado(BigDecimal precioFeriado) {
        this.precioFeriado = precioFeriado;
    }

    public BigDecimal getPrecioHorasExtra() {
        return precioHorasExtra;
    }

    public void setPrecioHorasExtra(BigDecimal precioHorasExtra) {
        this.precioHorasExtra = precioHorasExtra;
    }

    public BigDecimal getPrecioNormal() {
        return precioNormal;
    }

    public void setPrecioNormal(BigDecimal precioNormal) {
        this.precioNormal = precioNormal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
