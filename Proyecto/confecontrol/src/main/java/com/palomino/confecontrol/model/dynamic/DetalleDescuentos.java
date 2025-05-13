package com.palomino.confecontrol.model.dynamic;

import com.palomino.confecontrol.model.fixed.TipoDescuento;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class DetalleDescuentos {
    @Id
    @Column(name = "id_detalle_descuento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tipo_descuento_id", nullable = false)
    private TipoDescuento tipoDescuento;

    @Column(nullable = false)
    private BigDecimal monto;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public TipoDescuento getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(TipoDescuento tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
