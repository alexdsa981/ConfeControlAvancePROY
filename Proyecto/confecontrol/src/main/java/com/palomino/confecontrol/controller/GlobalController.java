package com.palomino.confecontrol.controller;

import com.palomino.confecontrol.model.dynamic.Usuario;
import com.palomino.confecontrol.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalController {
@Autowired
UsuarioService usuarioService;

    @ModelAttribute
    public void datosHeader(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
                !authentication.getPrincipal().equals("anonymousUser")) {
            Usuario usuario = usuarioService.getUsuarioPorId(usuarioService.getIDdeUsuarioLogeado());
            model.addAttribute("nombreUsuario", usuario.getNombre());
            LocalDateTime now = LocalDateTime.now(); // Hora actual
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = now.format(formatter); // Hora formateada
            model.addAttribute("horaActual", formattedTime);

        }
    }

}
