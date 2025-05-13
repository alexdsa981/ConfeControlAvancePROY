package com.palomino.confecontrol.model.dynamic;

import com.palomino.confecontrol.model.fixed.OperacionPrenda;
import com.palomino.confecontrol.model.fixed.Prenda;
import jakarta.persistence.*;

@Entity
public class DetallePaqueteLote {
    public DetallePaqueteLote() {
    }

    @Id
    @Column(name = "id_detalle_paquete_lote")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "operacion_prenda_id", nullable = false)
    private OperacionPrenda operacionPrenda;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario trabajador;

    @ManyToOne
    @JoinColumn(name = "paquete_lote_id", nullable = false)
    private PaqueteLote paqueteLote;

    String Observacion;


    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String observacion) {
        Observacion = observacion;
    }

    public OperacionPrenda getOperacionPrenda() {
        return operacionPrenda;
    }

    public void setOperacionPrenda(OperacionPrenda operacionPrenda) {
        this.operacionPrenda = operacionPrenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaqueteLote getPaqueteLote() {
        return paqueteLote;
    }

    public void setPaqueteLote(PaqueteLote paqueteLote) {
        this.paqueteLote = paqueteLote;
    }

    public Usuario getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Usuario trabajador) {
        this.trabajador = trabajador;
    }
}
