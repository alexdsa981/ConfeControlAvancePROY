package com.palomino.confecontrol.controller;

import com.palomino.confecontrol.model.dto.LoteConPaquetesDTO;
import com.palomino.confecontrol.model.dynamic.DetallePaqueteLote;
import com.palomino.confecontrol.model.dynamic.Lote;
import com.palomino.confecontrol.model.dynamic.PaqueteLote;
import com.palomino.confecontrol.model.dynamic.Usuario;
import com.palomino.confecontrol.model.fixed.OperacionPrenda;
import com.palomino.confecontrol.model.fixed.Prenda;
import com.palomino.confecontrol.repository.*;
import com.palomino.confecontrol.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/app/lote")
public class LoteController {
    @Autowired
    LoteRepository loteRepository;
    @Autowired
    PrendaRepository prendaRepository;
    @Autowired
    PaqueteLoteRepository paqueteLoteRepository;
    @Autowired
    DetallePaqueteLoteRepository detallePaqueteLoteRepository;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    OperacionPrendaRepository operacionPrendaRepository;

    public Model listarLotes(Model model) {
        model.addAttribute("ListaLotes", loteRepository.findAll());
        return model;
    }

    public Model listarPaquetes(Model model, Long idLote) {
        if (idLote != null) {
            model.addAttribute("ListaPaquetes", paqueteLoteRepository.findByLoteId(idLote));
        } else {
            model.addAttribute("ListaPaquetes", paqueteLoteRepository.findAll());
        }
        return model;
    }


    public Model listarDetallePaquetes(Model model, Long paqueteId) {
        if (paqueteId != null) {
            model.addAttribute("ListaDetallePaquetes", detallePaqueteLoteRepository.findByPaqueteLoteId(paqueteId));
        } else {
            model.addAttribute("ListaDetallePaquetes", detallePaqueteLoteRepository.findAll());
        }
        return model;
    }


    @PostMapping("/guardar")
    @ResponseBody
    public ResponseEntity<String> guardarLoteConPaquetes(@RequestBody LoteConPaquetesDTO dto) {
        if (dto.getPaquetes() == null || dto.getPaquetes().isEmpty()) {
            return ResponseEntity.badRequest().body("Debe incluir al menos un paquete.");
        }

        // Obtener prenda
        Prenda prendaSeleccionada = prendaRepository.findById(dto.getTipoPrendaId()).orElse(null);
        if (prendaSeleccionada == null) {
            return ResponseEntity.badRequest().body("Prenda no encontrada.");
        }

        // Crear lote
        Lote nuevoLote = new Lote();
        nuevoLote.setCantidadPrenda(dto.getCantidadLote());
        nuevoLote.setFechaCreacion(LocalDate.now());
        nuevoLote.setHoraCreacion(LocalTime.now());
        nuevoLote.setIsActive(true);
        nuevoLote.setIsTerminado(false);

        nuevoLote.setPrenda(prendaSeleccionada);
        nuevoLote.setCreadorLote(usuarioService.getUsuarioPorId(usuarioService.getIDdeUsuarioLogeado()));
        nuevoLote.setCodigo(generarCodigoLote());

        loteRepository.save(nuevoLote);

        List<OperacionPrenda> operaciones = prendaSeleccionada.getListaOperaciones();
        Usuario supervisor = usuarioService.getUsuarioPorId(dto.getSupervisorId());

        int numeroPaquete = 1;
        for (Integer cantidadPrendasPaquete : dto.getPaquetes()) {
            // Crear paquete
            PaqueteLote paquete = new PaqueteLote();
            paquete.setCantidad(cantidadPrendasPaquete);
            paquete.setLote(nuevoLote);
            paquete.setSupervisorPaqueteLote(supervisor);
            paquete.setCodigo(generarCodigoPaquete(nuevoLote.getCodigo(), numeroPaquete++));
            paquete.setIsTerminado(false);
            paqueteLoteRepository.save(paquete);

            // Crear detalle por cada operación * cantidad del paquete
            for (OperacionPrenda operacion : operaciones) {
                DetallePaqueteLote detalle = new DetallePaqueteLote();
                detalle.setIsTerminado(false);
                detalle.setOperacionPrenda(operacion);
                detalle.setPaqueteLote(paquete);
                detallePaqueteLoteRepository.save(detalle);
            }

        }

        return ResponseEntity.ok("Lote y paquetes guardados exitosamente.");
    }


    public String generarCodigoLote() {
        LocalDate hoy = LocalDate.now();
        String fechaFormateada = hoy.format(DateTimeFormatter.ofPattern("ddMMyy"));
        long countHoy = loteRepository.countByFechaCreacion(hoy);
        String secuencia = String.format("%02d", countHoy + 1); // solo 2 dígitos
        return "LOTE-" + fechaFormateada + "-" + secuencia;
    }

    public String generarCodigoPaquete(String codigoLote, int numeroPaquete) {
        String secuencia = String.format("%02d", numeroPaquete); // solo 2 dígitos
        return codigoLote + "-PAQ" + secuencia;
    }


    @GetMapping("/operaciones-por-lote/{idLote}")
    @ResponseBody
    public List<OperacionPrenda> obtenerOperacionesPorLote(@PathVariable Long idLote) {
        Optional<Lote> loteOptional = loteRepository.findById(idLote);
        if (loteOptional.isPresent()) {
            Prenda prenda = loteOptional.get().getPrenda();
            return prenda.getListaOperaciones();
        }
        return Collections.emptyList();
    }


    @PostMapping("/asignar-operario")
    public ResponseEntity<?> asignarOperario(
            @RequestParam Long loteId,
            @RequestParam Long operacionId,
            @RequestParam Long operarioId) {

        Optional<Lote> loteOpt = loteRepository.findById(loteId);
        Optional<OperacionPrenda> operacionOpt = operacionPrendaRepository.findById(operacionId);

        if (loteOpt.isEmpty() || operacionOpt.isEmpty() || operarioId == null) {
            return ResponseEntity.badRequest().body("Datos inválidos.");
        }

        Usuario usuario = usuarioService.getUsuarioPorId(operarioId);
        if (usuario == null) {
            return ResponseEntity.badRequest().body("Operario no encontrado.");
        }

        List<DetallePaqueteLote> detalles = detallePaqueteLoteRepository.findByLoteIdAndOperacionId(loteId, operacionId);

        for (DetallePaqueteLote detalle : detalles) {
            detalle.setTrabajador(usuario);
        }

        detallePaqueteLoteRepository.saveAll(detalles);

        return ResponseEntity.ok("Operario asignado correctamente a " + detalles.size() + " operaciones.");
    }

}
