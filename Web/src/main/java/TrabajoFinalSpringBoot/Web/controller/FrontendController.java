package TrabajoFinalSpringBoot.Web.controller;

import TrabajoFinalSpringBoot.Web.model.LibroFrontend;
import TrabajoFinalSpringBoot.Web.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FrontendController {
    
    // Inyectamos nuestro Service, ¡dejando el Controller limpio!
    @Autowired
    private ApiService service;

    @GetMapping("/")
    public String index(Model model, 
                        @RequestParam(required = false) String titulo,
                        @RequestParam(required = false) String autor,
                        @RequestParam(required = false) Integer inicio,
                        @RequestParam(required = false) Integer fin) {
        try {
            List<LibroFrontend> libros = service.obtenerTodosLosLibros(titulo, autor, inicio, fin);
            model.addAttribute("libros", libros);
        } catch (Exception e) {
            model.addAttribute("error", "Error: No se pudo contectar con la API (puerto 8081).");
        }
        return "index";
    }

    @GetMapping("/nuevo")
    public String nuevoLibro(Model model) {
        model.addAttribute("libro", new LibroFrontend());
        return "add-libro";
    }

    @PostMapping("/guardar")
    public String guardarLibro(@ModelAttribute LibroFrontend libro) {
        try {
            service.guardarLibro(libro);
        } catch(Exception e) { }
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String editarLibro(@PathVariable Long id, Model model) {
        try {
            LibroFrontend editado = service.obtenerLibroPorId(id);
            if(editado == null) editado = new LibroFrontend();
            model.addAttribute("libro", editado);
        } catch(Exception e) {
            model.addAttribute("libro", new LibroFrontend());
        }
        return "add-libro";
    }

    @GetMapping("/borrar/{id}")
    public String borrarLibro(@PathVariable Long id) {
        try {
            service.borrarLibro(id);
        } catch(Exception e) {}
        return "redirect:/";
    }

    @GetMapping("/cv")
    public String mostrarCV() {
        return "cv";
    }
}
