package com.palomino.confecontrol.model.dynamic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.palomino.confecontrol.model.fixed.RolUsuario;
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Entity
public class Usuario {
    public Usuario() {
    }

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String dni;

    private String numCelular;
    private String correo;

    @Column(nullable = false)
    private Boolean isActive;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "rol_usuario_id", nullable = false)
    private RolUsuario rolUsuario;


    @Column(nullable = false)
    private Boolean changedPass;


    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Marcacion> listaMarcaciones;

    @JsonIgnore
    @OneToMany(mappedBy = "creadorLote")
    private List<Lote> listaLotes;

    @JsonIgnore
    @OneToMany(mappedBy = "supervisorPaqueteLote")
    private List<PaqueteLote> listaPaqueteLotes;

    @JsonIgnore
    @OneToMany(mappedBy = "trabajador")
    private List<DetallePaqueteLote> listaDetallePaqueteLote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getChangedPass() {
        return changedPass;
    }

    public void setChangedPass(Boolean changedPass) {
        this.changedPass = changedPass;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNumCelular() {
        return numCelular;
    }

    public void setNumCelular(String numCelular) {
        this.numCelular = numCelular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public RolUsuario getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public List<Marcacion> getListaMarcaciones() {
        return listaMarcaciones;
    }

    public void setListaMarcaciones(List<Marcacion> listaMarcaciones) {
        this.listaMarcaciones = listaMarcaciones;
    }

    public List<Lote> getListaLotes() {
        return listaLotes;
    }

    public void setListaLotes(List<Lote> listaLotes) {
        this.listaLotes = listaLotes;
    }

    public List<PaqueteLote> getListaPaqueteLotes() {
        return listaPaqueteLotes;
    }

    public void setListaPaqueteLotes(List<PaqueteLote> listaPaqueteLotes) {
        this.listaPaqueteLotes = listaPaqueteLotes;
    }

    public List<DetallePaqueteLote> getListaDetallePaqueteLote() {
        return listaDetallePaqueteLote;
    }

    public void setListaDetallePaqueteLote(List<DetallePaqueteLote> listaDetallePaqueteLote) {
        this.listaDetallePaqueteLote = listaDetallePaqueteLote;
    }

    public void asignarYEncriptarPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }
}
