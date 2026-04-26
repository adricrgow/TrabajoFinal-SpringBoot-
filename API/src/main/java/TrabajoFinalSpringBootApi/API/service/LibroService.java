package TrabajoFinalSpringBootApi.API.service;

import TrabajoFinalSpringBootApi.API.model.Libro;
import TrabajoFinalSpringBootApi.API.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Aquí pongo toda la lógica de negocio. El controlador llama a este servicio, 
// y este servicio se comunica con el repositorio (base de datos).
@Service
public class LibroService {

    @Autowired
    private LibroRepository repository;

    // CREATE - Crear un libro nuevo
    public Libro crear(Libro libro) {
        return repository.save(libro); // JPA le asigna el ID de forma automática
    }

    // READ - Obtener todos los libros de la base de datos
    public List<Libro> listar() {
        return repository.findAll();
    }

    // READ - Obtener un libro en específico usando su ID
    public Libro buscarPorId(long id) {
        return repository.findById(id).orElse(null); // Devuelvo null si no lo encuentra
    }

    // UPDATE - Actualizar los datos de un libro existente
    public Libro actualizar(long id, Libro libroActualizado) {
        Libro existente = repository.findById(id).orElse(null);
        if (existente != null) {
            // Actualizo campo por campo
            existente.setTitulo(libroActualizado.getTitulo());
            existente.setAutor(libroActualizado.getAutor());
            existente.setPrecio(libroActualizado.getPrecio());
            existente.setAnio(libroActualizado.getAnio());
            existente.setImagen(libroActualizado.getImagen());
            existente.setDescripcion(libroActualizado.getDescripcion());
            return repository.save(existente); // Guardo los cambios
        }
        return null; // Si no existe, devuelvo null
    }

    // DELETE - Eliminar un libro
    public String eliminar(long id) {
        Libro existente = repository.findById(id).orElse(null);
        if (existente != null) {
            repository.deleteById(id);
            return "Libro " + id + " eliminado";
        }
        return "Libro " + id + " no encontrado";
    }

    // MÉTODOS DE BÚSQUEDA PERSONALIZADOS (Llaman a los métodos mágicos del repositorio)
    
    public List<Libro> buscarPorTitulo(String titulo) {
        return repository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Libro> buscarPorAutor(String autor) {
        return repository.findByAutorContainingIgnoreCase(autor);
    }

    public List<Libro> buscarEntreAnios(Integer inicio, Integer fin) {
        return repository.findByAnioBetween(inicio, fin);
    }

    public List<Libro> buscarPorDescripcion(String descripcion) {
        return repository.findByDescripcionContainingIgnoreCase(descripcion);
    }
}
