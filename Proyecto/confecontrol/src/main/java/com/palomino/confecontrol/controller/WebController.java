package com.palomino.confecontrol.controller;


import com.palomino.confecontrol.model.fixed.Prenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class WebController {

    @Autowired
    UsuarioController usuarioController;
    @Autowired
    PrendaController prendaController;

    //redirige / a /login
    @GetMapping("/")
    public String redirectToInicio() {
        return "redirect:/login";
    }

    //abre index.html en /login
    @GetMapping("/login")
    public String redirigePaginaLogin(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "username", required = false) String username,
            Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() &&
                !authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/inicio"; // Redirige a la vista de inicio
        }

        model.addAttribute("error", error);
        model.addAttribute("username", username);

        return "index";
    }
    @GetMapping("/marcacion")
    public String redirigePaginaMarcacion(Model model) {
        model.addAttribute("Titulo", "ConFeControl | Marcación de Asistencia");
        return "marcacion";
    }

    @GetMapping("/inicio")
    public String redirigePaginaInicio(Model model) {
        model.addAttribute("SubTitulo", "Bienvenido :)");
        model.addAttribute("Titulo", "ConFeControl | Inicio");
        return "general/inicio";
    }

    @GetMapping("/prendas")
    public String redirigePaginaPrenda(@RequestParam(value = "selectedId", required = false) Long selectedId, Model model) {
        prendaController.listarPrendas(model);
        prendaController.prendaSeleccionada(selectedId, model);
        model.addAttribute("SubTitulo", "Gestión de Prendas");
        model.addAttribute("Titulo", "ConFeControl | Gestión de Prendas");
        return "general/prendas";
    }

    @GetMapping("/lotes")
    public String redirigePaginaLotes(Model model) {
        model.addAttribute("SubTitulo", "Gestión de Lotes");
        model.addAttribute("Titulo", "ConFeControl | Gestión de Lotes");
        return "general/lotes";
    }

    @GetMapping("admin/usuarios")
    public String redirigePaginaUsuario(Model model) {
        usuarioController.listarRoles(model);
        usuarioController.listarUsuariosActivos(model);
        usuarioController.listarUsuariosDesactivados(model);
        model.addAttribute("SubTitulo", "Gestión de Usuarios");
        model.addAttribute("Titulo", "ConFeControl | Gestión de Usuarios");
        return "admin/usuarios";
    }

    @GetMapping("admin/asistencia")
    public String redirigePaginaMarcaciones(Model model) {
        model.addAttribute("SubTitulo", "Historial de Marcaciones");
        model.addAttribute("Titulo", "ConFeControl | Historial de Marcaciones");
        return "admin/asistencia";
    }

    @GetMapping("admin/dashboard")
    public String redirigePaginaDashboard(Model model) {
        model.addAttribute("SubTitulo", "Dashboard");
        model.addAttribute("Titulo", "ConFeControl | Dashboard");
        return "admin/dashboard";
    }

    @GetMapping("admin/pagos")
    public String redirigePaginaPagos(Model model) {
        model.addAttribute("SubTitulo", "Gestión de Pagos");
        model.addAttribute("Titulo", "ConFeControl | Pagos");
        return "admin/pagos";
    }
}
