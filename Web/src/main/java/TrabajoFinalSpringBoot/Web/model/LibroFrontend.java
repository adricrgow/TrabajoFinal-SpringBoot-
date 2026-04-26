package TrabajoFinalSpringBoot.Web.model;

// Esta clase es como un espejo del modelo Libro de la API.
// La uso en el Frontend para recibir y enviar los datos en formato JSON a través del RestTemplate.
public class LibroFrontend {
    // Los mismos campos que en la API para que coincida todo perfectamente al conectarlos
    private Long id;
    private String titulo;
    private String autor;
    private Double precio;
    private Integer anio;
    private String imagen;
    private String descripcion;

    // Getters y setters básicos
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
