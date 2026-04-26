package TrabajoFinalSpringBootApi.API.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Esta es mi clase de modelo para los Libros.
// Le pongo el @Entity para que JPA me mapee esto a una tabla en la base de datos de forma automática.
@Entity
public class Libro {
    
    // El ID será mi clave primaria y le digo que se genere solo (autoincremental)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Los campos que va a tener cada libro en mi aplicación
    private String titulo;
    private String autor;
    private Double precio;
    private Integer anio; // usamos 'anio' para evitar problemas con la 'ñ' al programar
    private String imagen;
    private String descripcion;
    
    // Genero los Getters y Setters para que Spring pueda leer y guardar los datos correctamente
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
