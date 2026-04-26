package TrabajoFinalSpringBoot.Web.service;

import TrabajoFinalSpringBoot.Web.model.LibroFrontend;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

// Este servicio es vital: hace de "puente" entre mi Frontend web y mi API REST.
// Utilizo RestTemplate para hacer peticiones HTTP (GET, POST, PUT, DELETE) a la API.
@Service
public class ApiService {

    // La URL base donde corre mi API en local
    private final String API_LIBROS = "http://localhost:8081/api/libros";
    private final RestTemplate restTemplate = new RestTemplate();

    // Obtener libros (soporta los filtros de búsqueda que armé en la API)
    public List<LibroFrontend> obtenerTodosLosLibros(String titulo, String autor, String descripcion, Integer inicio, Integer fin) {
        String url = API_LIBROS;
        
        // Voy montando la URL según los filtros que me lleguen desde la vista
        if (inicio != null && fin != null) {
            url += "/buscar/anios?inicio=" + inicio + "&fin=" + fin;
        } else if (autor != null && !autor.isEmpty()) {
            url += "/buscar/autor?autor=" + autor;
        } else if (titulo != null && !titulo.isEmpty()) {
            url += "/buscar/titulo?titulo=" + titulo;
        } else if (descripcion != null && !descripcion.isEmpty()) {
            url += "/buscar/descripcion?descripcion=" + descripcion;
        }
        
        // Hago un GET a la URL final y mapeo la respuesta a una lista de LibroFrontend
        ResponseEntity<List<LibroFrontend>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<LibroFrontend>>() {}
        );
        return response.getBody();
    }

    // Crear o actualizar un libro dependiendo de si tiene ID o no
    public void guardarLibro(LibroFrontend libro) {
        if (libro.getId() != null) {
            // Si tiene ID, es que ya existe, así que hago un PUT para actualizar
            restTemplate.put(API_LIBROS + "/" + libro.getId(), libro);
        } else {
            // Si no tiene ID, es nuevo, así que hago un POST para crearlo
            restTemplate.postForObject(API_LIBROS, libro, LibroFrontend.class);
        }
    }

    // Buscar un solo libro por su ID (para cargar los datos en el formulario de editar)
    public LibroFrontend obtenerLibroPorId(Long id) {
        // Hago una petición para traer todos y luego filtro por ID 
        // (podría mejorarlo llamando a un endpoint /api/libros/{id} en el futuro)
        ResponseEntity<List<LibroFrontend>> response = restTemplate.exchange(
            API_LIBROS, HttpMethod.GET, null, new ParameterizedTypeReference<List<LibroFrontend>>() {}
        );
        return response.getBody().stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Borrar un libro de la API
    public void borrarLibro(Long id) {
        restTemplate.delete(API_LIBROS + "/" + id);
    }

    // Actualizar libro (método suelto por si acaso lo necesito, aunque guardarLibro ya lo hace)
    public void actualizarLibro(LibroFrontend libro) {
        restTemplate.put(API_LIBROS + "/" + libro.getId(), libro);
    }
}
