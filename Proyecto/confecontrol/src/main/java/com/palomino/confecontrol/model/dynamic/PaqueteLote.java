package com.palomino.confecontrol.model.dynamic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.palomino.confecontrol.model.fixed.Prenda;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class PaqueteLote {

    public PaqueteLote() {
    }

    @Id
    @Column(name = "id_paquete_lote")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario supervisorPaqueteLote;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "lote_id", nullable = false)
    private Lote lote;



    @JsonIgnore
    @OneToMany(mappedBy = "paqueteLote")
    private List<DetallePaqueteLote> listaDetallePaqueteLote;



    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public List<DetallePaqueteLote> getListaDetallePaqueteLote() {
        return listaDetallePaqueteLote;
    }

    public void setListaDetallePaqueteLote(List<DetallePaqueteLote> listaDetallePaqueteLote) {
        this.listaDetallePaqueteLote = listaDetallePaqueteLote;
    }

    public Usuario getSupervisorPaqueteLote() {
        return supervisorPaqueteLote;
    }

    public void setSupervisorPaqueteLote(Usuario supervisorPaqueteLote) {
        this.supervisorPaqueteLote = supervisorPaqueteLote;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
