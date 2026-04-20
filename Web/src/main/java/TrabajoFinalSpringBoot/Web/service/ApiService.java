package TrabajoFinalSpringBoot.Web.service;

import TrabajoFinalSpringBoot.Web.model.LibroFrontend;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiService {

    private final String API_LIBROS = "http://localhost:8081/api/libros";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<LibroFrontend> obtenerTodosLosLibros(String titulo, String autor, Integer inicio, Integer fin) {
        String url = API_LIBROS;
        
        if (inicio != null && fin != null) {
            url += "/buscar/anios?inicio=" + inicio + "&fin=" + fin;
        } else if (autor != null && !autor.isEmpty()) {
            url += "/buscar/autor?autor=" + autor;
        } else if (titulo != null && !titulo.isEmpty()) {
            url += "/buscar/titulo?titulo=" + titulo;
        }
        
        ResponseEntity<List<LibroFrontend>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<LibroFrontend>>() {}
        );
        return response.getBody();
    }

    public void guardarLibro(LibroFrontend libro) {
        if (libro.getId() != null) {
            restTemplate.put(API_LIBROS + "/" + libro.getId(), libro);
        } else {
            restTemplate.postForObject(API_LIBROS, libro, LibroFrontend.class);
        }
    }

    public LibroFrontend obtenerLibroPorId(Long id) {
        ResponseEntity<List<LibroFrontend>> response = restTemplate.exchange(
            API_LIBROS, HttpMethod.GET, null, new ParameterizedTypeReference<List<LibroFrontend>>() {}
        );
        return response.getBody().stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void borrarLibro(Long id) {
        restTemplate.delete(API_LIBROS + "/" + id);
    }

    public void actualizarLibro(LibroFrontend libro) {
        restTemplate.put(API_LIBROS + "/" + libro.getId(), libro);
    }
}
