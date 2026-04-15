package TrabajoFinalSpringBootApi.API.service;

import TrabajoFinalSpringBootApi.API.model.Libro;
import TrabajoFinalSpringBootApi.API.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository repository;

    // CREATE
    public Libro crear(Libro libro) {
        return repository.save(libro); // JPA asigna ID automáticamente
    }

    // READ - obtener todos
    public List<Libro> listar() {
        return repository.findAll();
    }

    // READ - obtener por id
    public Libro buscarPorId(long id) {
        return repository.findById(id).orElse(null);
    }

    // UPDATE
    public Libro actualizar(long id, Libro libroActualizado) {
        Libro existente = repository.findById(id).orElse(null);
        if (existente != null) {
            existente.setTitulo(libroActualizado.getTitulo());
            existente.setAutor(libroActualizado.getAutor());
            existente.setPrecio(libroActualizado.getPrecio());
            existente.setAnio(libroActualizado.getAnio());
            existente.setImagen(libroActualizado.getImagen());
            return repository.save(existente);
        }
        return null; // no existe
    }

    // DELETE
    public String eliminar(long id) {
        Libro existente = repository.findById(id).orElse(null);
        if (existente != null) {
            repository.deleteById(id);
            return "Libro " + id + " eliminado";
        }
        return "Libro " + id + " no encontrado";
    }

    // BUSCAR
    public List<Libro> buscarPorTitulo(String titulo) {
        return repository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Libro> buscarPorAutor(String autor) {
        return repository.findByAutorContainingIgnoreCase(autor);
    }

    public List<Libro> buscarEntreAnios(Integer inicio, Integer fin) {
        return repository.findByAnioBetween(inicio, fin);
    }
}
