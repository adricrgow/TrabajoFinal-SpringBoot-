package TrabajoFinalSpringBootApi.API.controller;

import TrabajoFinalSpringBootApi.API.model.Libro;
import TrabajoFinalSpringBootApi.API.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "*") // Permite conectarse desde frontend diferente sin error de CORS
public class LibroController {

    @Autowired
    private LibroService service;

    // Listar todos los libros
    @GetMapping
    public List<Libro> listarLibros() {
        return service.listar();
    }

    // Añadir libro
    @PostMapping
    public Libro anadirLibro(@RequestBody Libro libro) {
        return service.crear(libro);
    }

    // Editar libro
    @PutMapping("/{id}")
    public Libro editarLibro(@PathVariable Long id, @RequestBody Libro libroActualizado) {
        Libro actualizado = service.actualizar(id, libroActualizado);
        if (actualizado == null) {
            throw new RuntimeException("Libro no encontrado con id " + id);
        }
        return actualizado;
    }

    // Borrar libro
    @DeleteMapping("/{id}")
    public String borrarLibro(@PathVariable Long id) {
        return service.eliminar(id);
    }

    // Buscar libro por título
    @GetMapping("/buscar/titulo")
    public List<Libro> buscarPorTitulo(@RequestParam String titulo) {
        return service.buscarPorTitulo(titulo);
    }

    // Buscar libros por autor
    @GetMapping("/buscar/autor")
    public List<Libro> buscarPorAutor(@RequestParam String autor) {
        return service.buscarPorAutor(autor);
    }

    // Buscar entre años
    @GetMapping("/buscar/anios")
    public List<Libro> buscarEntreAnios(@RequestParam Integer inicio, @RequestParam Integer fin) {
        return service.buscarEntreAnios(inicio, fin);
    }

    // Buscar por descripción
    @GetMapping("/buscar/descripcion")
    public List<Libro> buscarPorDescripcion(@RequestParam String descripcion) {
        return service.buscarPorDescripcion(descripcion);
    }
}
