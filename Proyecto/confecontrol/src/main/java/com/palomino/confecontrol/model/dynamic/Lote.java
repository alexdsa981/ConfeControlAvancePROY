package com.palomino.confecontrol.model.dynamic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.palomino.confecontrol.model.fixed.Prenda;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Lote {
    public Lote() {
    }

    @Id
    @Column(name = "id_lote")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;
    @Column(nullable = false)
    private int cantidadPrenda;


    @ManyToOne
    @JoinColumn(name = "prenda_id", nullable = false)
    private Prenda prenda;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario creadorLote;

    @JsonIgnore
    @OneToMany(mappedBy = "lote")
    private List<PaqueteLote> listaPaquetes;


    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private LocalDate fechaCreacion;
    @Column(nullable = false)
    private LocalDate fechaFin;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Prenda getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda prenda) {
        this.prenda = prenda;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<PaqueteLote> getListaPaquetes() {
        return listaPaquetes;
    }

    public void setListaPaquetes(List<PaqueteLote> listaPaquetes) {
        this.listaPaquetes = listaPaquetes;
    }

    public Usuario getCreadorLote() {
        return creadorLote;
    }

    public void setCreadorLote(Usuario creadorLote) {
        this.creadorLote = creadorLote;
    }


    public int getCantidadPrenda() {
        return cantidadPrenda;
    }

    public void setCantidadPrenda(int cantidadPrenda) {
        this.cantidadPrenda = cantidadPrenda;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
