package TrabajoFinalSpringBoot.Web.controller;

import TrabajoFinalSpringBoot.Web.model.LibroFrontend;
import TrabajoFinalSpringBoot.Web.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Este controlador maneja las vistas de la página web
@Controller
public class FrontendController {
    
    // Inyectamos nuestro Service que se comunica con la API
    @Autowired
    private ApiService service;

    // Página principal donde muestro los libros y manejo los filtros de búsqueda
    @GetMapping("/")
    public String index(Model model, 
                        @RequestParam(required = false) String titulo,
                        @RequestParam(required = false) String autor,
                        @RequestParam(required = false) Integer inicio,
                        @RequestParam(required = false) String descripcion,
                        @RequestParam(required = false) Integer fin) {
        try {
            // Llama al servicio pasándole los parámetros de búsqueda (si los hay)
            List<LibroFrontend> libros = service.obtenerTodosLosLibros(titulo, autor, descripcion, inicio, fin);
            model.addAttribute("libros", libros);
        } catch (Exception e) {
            // Si la API está apagada, capturo el error y muestro un mensaje en pantalla
            model.addAttribute("error", "Error: No se pudo conectar con la API (puerto 8081). ¡Asegúrate de encenderla!");
        }
        return "index";
    }

    // Mostrar formulario para añadir un nuevo libro
    @GetMapping("/nuevo")
    public String nuevoLibro(Model model) {
        model.addAttribute("libro", new LibroFrontend());
        return "add-libro";
    }

    // Guardar el libro (tanto si es nuevo como si lo estamos editando)
    @PostMapping("/guardar")
    public String guardarLibro(@ModelAttribute LibroFrontend libro) {
        try {
            service.guardarLibro(libro);
        } catch(Exception e) { 
            // Podría añadir logs aquí en el futuro
        }
        return "redirect:/"; // Vuelvo al inicio después de guardar
    }

    // Mostrar el formulario pero con los datos cargados para editar
    @GetMapping("/editar/{id}")
    public String editarLibro(@PathVariable Long id, Model model) {
        try {
            LibroFrontend editado = service.obtenerLibroPorId(id);
            if(editado == null) editado = new LibroFrontend();
            model.addAttribute("libro", editado);
        } catch(Exception e) {
            model.addAttribute("libro", new LibroFrontend());
        }
        return "add-libro"; // Reutilizo la misma vista que para crear
    }

    // Borrar un libro pulsando el botón de la papelera
    @GetMapping("/borrar/{id}")
    public String borrarLibro(@PathVariable Long id) {
        try {
            service.borrarLibro(id);
        } catch(Exception e) {}
        return "redirect:/"; // Recargo la página inicial
    }

    // Página de mi currículum
    @GetMapping("/cv")
    public String mostrarCV() {
        return "cv";
    }

    // Una vista alternativa para listar libros si la necesito
    @GetMapping("/libros")
    public String listarLibros(Model model) {
        try {
            List<LibroFrontend> libros = service.obtenerTodosLosLibros(null, null, null, null, null);
            model.addAttribute("libros", libros);
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar los libros.");
        }
        return "libros";
    }
}
