package com.palomino.confecontrol.model.fixed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.palomino.confecontrol.model.dynamic.Lote;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Prenda {
    public Prenda() {
    }

    @Id
    @Column(name = "id_prenda")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private BigDecimal costoTotal;

    @Column(nullable = false)
    private Boolean isActive;

    @JsonIgnore
    @OneToMany(mappedBy = "prenda")
    private List<Lote> listaLotes;

    @JsonIgnore
    @OneToMany(mappedBy = "prenda")
    private List<OperacionPrenda> listaOperaciones;

    @JsonIgnore
    @OneToMany(mappedBy = "prenda")
    private List<OperacionPrenda> listaPiezas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OperacionPrenda> getListaPiezas() {
        return listaPiezas;
    }

    public List<Lote> getListaLotes() {
        return listaLotes;
    }

    public void setListaLotes(List<Lote> listaLotes) {
        this.listaLotes = listaLotes;
    }

    public void setListaPiezas(List<OperacionPrenda> listaPiezas) {
        this.listaPiezas = listaPiezas;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public List<OperacionPrenda> getListaOperaciones() {
        return listaOperaciones;
    }

    public void setListaOperaciones(List<OperacionPrenda> listaOperaciones) {
        this.listaOperaciones = listaOperaciones;
    }
}
