package com.palomino.confecontrol.model.dto;

import com.palomino.confecontrol.model.dynamic.Lote;
import com.palomino.confecontrol.model.dynamic.Usuario;

import java.util.List;

public class pagoPorUsuarioDTO {
    private Usuario usuario;
    //aqui colocar los lotes por semana
    private List<Lote> lotesTrabajados;
}
