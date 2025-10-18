
package gt.edu.miumg.bienestar.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "servicios")
public class Servicio {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long idServicio;
    
    @Column(nullable = false, unique = true)
    private String nombre;
    
    @Column(length = 255)
    private String descripcion;
    
    @Column(nullable = false)
    private int precio;
    
    @Column(name = "duracion_minutos", nullable = false)
    private int duracionMinutos;
    
    //Cantidad maxima de citas simultaneas permitidas en una franja horaria
    @Column(name = "max_concurrentes", nullable = false)
    private int maxConcurrentes = 1;
    
    @Column(name = "activo", nullable = false)
    private boolean activo = true;
    
    //un servicio puede tener muchas citas
    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
    private List<Cita> citas;

    public Servicio(Long idServicio, String descripcion, int precio, int duracionMinutos, List<Cita> citas) {
        this.idServicio = idServicio;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracionMinutos = duracionMinutos;
        this.citas = citas;
    }

    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public int getMaxConcurrentes() {
        return maxConcurrentes;
    }

    public void setMaxConcurrentes(int maxConcurrentes) {
        this.maxConcurrentes = maxConcurrentes;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
    

}
