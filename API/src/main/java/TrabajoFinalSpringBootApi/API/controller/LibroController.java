package TrabajoFinalSpringBootApi.API.controller;

import TrabajoFinalSpringBootApi.API.model.Libro;
import TrabajoFinalSpringBootApi.API.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Este es el controlador principal para la API de libros.
// Aquí defino todas las rutas para las operaciones CRUD y las diferentes búsquedas.
@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "*") // Permite conectarse desde frontend diferente sin error de CORS (muy útil para que no falle la web)
public class LibroController {

    // Inyecto el servicio donde tengo toda la lógica de negocio
    @Autowired
    private LibroService service;

    // Listar todos los libros que tengo en la base de datos
    @GetMapping
    public List<Libro> listarLibros() {
        return service.listar();
    }

    // Añadir un nuevo libro (lo recibo en el body de la petición)
    @PostMapping
    public Libro anadirLibro(@RequestBody Libro libro) {
        return service.crear(libro);
    }

    // Editar un libro existente buscándolo por su ID
    @PutMapping("/{id}")
    public Libro editarLibro(@PathVariable Long id, @RequestBody Libro libroActualizado) {
        Libro actualizado = service.actualizar(id, libroActualizado);
        // Si no encuentro el libro, lanzo una excepción para enterarme del error
        if (actualizado == null) {
            throw new RuntimeException("Libro no encontrado con id " + id);
        }
        return actualizado;
    }

    // Borrar libro pasándole su ID en la URL
    @DeleteMapping("/{id}")
    public String borrarLibro(@PathVariable Long id) {
        return service.eliminar(id);
    }

    // Buscar libro por título (genial para la barra de búsqueda del frontend)
    @GetMapping("/buscar/titulo")
    public List<Libro> buscarPorTitulo(@RequestParam String titulo) {
        return service.buscarPorTitulo(titulo);
    }

    // Buscar libros por autor
    @GetMapping("/buscar/autor")
    public List<Libro> buscarPorAutor(@RequestParam String autor) {
        return service.buscarPorAutor(autor);
    }

    // Buscar libros filtrando por un rango de años
    @GetMapping("/buscar/anios")
    public List<Libro> buscarEntreAnios(@RequestParam Integer inicio, @RequestParam Integer fin) {
        return service.buscarEntreAnios(inicio, fin);
    }

    // Buscar libros por alguna palabra clave en su descripción
    @GetMapping("/buscar/descripcion")
    public List<Libro> buscarPorDescripcion(@RequestParam String descripcion) {
        return service.buscarPorDescripcion(descripcion);
    }
}
