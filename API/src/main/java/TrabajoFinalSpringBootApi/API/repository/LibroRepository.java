package TrabajoFinalSpringBootApi.API.repository;

import TrabajoFinalSpringBootApi.API.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Este es el repositorio que uso para acceder a la base de datos de libros.
// Como heredo de JpaRepository, ya tengo hechos los métodos básicos (guardar, borrar, buscar por ID, etc).
@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    
    // Buscar libro por título (Contiene la palabra sin importar mayúsculas)
    // Spring Data crea la query SQL automáticamente solo con leer el nombre del método
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
    
    // Buscar libros por autor
    List<Libro> findByAutorContainingIgnoreCase(String autor);
    
    // Buscar entre años (Usando los métodos especiales del enlace de GeeksforGeeks)
    List<Libro> findByAnioBetween(Integer startYear, Integer endYear);

    // Buscar por descripción
    List<Libro> findByDescripcionContainingIgnoreCase(String descripcion);
}
