package TrabajoFinalSpringBootApi.API.repository;

import TrabajoFinalSpringBootApi.API.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    
    // Buscar libro por título (Contiene la palabra sin importar mayúsculas)
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
    
    // Buscar libros por autor
    List<Libro> findByAutorContainingIgnoreCase(String autor);
    
    // Buscar entre años (Usando los métodos especiales del enlace de GeeksforGeeks)
    List<Libro> findByAnioBetween(Integer startYear, Integer endYear);
}
